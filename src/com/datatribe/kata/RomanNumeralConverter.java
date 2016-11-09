package com.datatribe.kata;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import com.datatribe.util.*;
import org.apache.log4j.*;
import java.io.*;
import java.util.*;

/**
 * Created by datatribe on 10/29/2016.
 *
 * A class to provide arabic number to roman numeral conversion, and the reverse.
 *
 * Following the rules for forming Roman Numerals found at the provided reference:
 * http://agilekatas.co.uk/katas/romannumerals-kata
 *
 */

public class RomanNumeralConverter {
    // public declarations

    // private declarations
    private Map<String, String> lookupList;
    private Map<String,String> reverseLookup;
    private static Logger logger = Utility.logger;
    private static Utility util;

    /**
     *  constructor
     *
     *  Sets up the lookup map and then automatically creates a reverse lookup map from that
      */
   public RomanNumeralConverter(){
        util = new Utility();
        lookupList = new TreeMap<>();
        reverseLookup = new TreeMap<>();
        buildReferenceData();
    }


    /////////////////
    // public methods

    /**
     *  String arabicToRoman - given a number from 1 to 3999, returns the Roman numeral equivalent
     *  following the provided rules.  Interesting notes on variations of rules, see "Alternative Forms" here:
     *  https://en.wikipedia.org/wiki/Roman_numerals
     *
     *  This function procedurally reduces the input int by powers of 10 to find the appropriate Roman Numeral
     *  components, assembling the output String as it goes.
     *
     * @param arabic input an int 1-3999
     * @return a properly constructed Roman Numeral representing the value of the input int
     */
    public String arabicToRoman(int arabic){
        String romanValue = "";

        if (arabic > 3999 || arabic < 1){
            return "Out of Range";
        }

        // interrogate arabic number for thousands
        romanValue += getRomanValuebyPower(1000, arabic);
        logger.debug("roman composite: " + romanValue);

        // reduce arabic to hundreds
        arabic = arabic % 1000;
        logger.debug("arabic hundreds " + String.valueOf(arabic));

        // interrogate arabic number for hundreds
        romanValue += getRomanValuebyPower(100, arabic);
        logger.debug("roman composite: " + romanValue);


        // reduce arabic to 10s
        arabic = arabic % 100;
        logger.debug("arabic tens " + String.valueOf(arabic));

        // interrogate arabic number for tens
        romanValue += getRomanValuebyPower(10, arabic);
        logger.debug("roman composite: " + romanValue);

        // reduce arabic to ones
        arabic = arabic % 10;
        logger.debug("arabic ones " + String.valueOf(arabic));

        // interrogate arabic number for 1s
        if(arabic < 10 && arabic > 0){
            romanValue += lookupList.get(String.valueOf(arabic));
            logger.debug("roman composite: " + romanValue);
        }


        return romanValue;
    }


    /**
     *  int romanToArabic - given an input Roman Numeral String I through MMMCMXCIX, finds the appropriate numeric
     *  elements and aggregates them to produce the output.  Uses the private recursive function lookupArabicFromRoman
     *
     *  This function uses brute force parsing, dropping the first character each iteration to see if a lookup value
     *  can be found, starting from the end of the list (highest values) and working backwards.  Returned integers are
     *  aggregated and the "found" portion of the roman numeral is removed from the original and the remaining
     *  Roman Numeral fragment is subjected to the same process until all characters have resulted in a successful
     *  lookup, at which point the aggregated integer is returned.
     *
     *  Invalid Roman Numerals will produce unexpected results.
     *
     * @param roman - a properly formatted Roman Numeral
     * @return - for valid Roman Numerals with a value between 1 and 3999, returns the int value
     */
    public int romanToArabic(String roman) {

        int aggregator = 0;
        int tmpval;

        try{
            while (!roman.equals("")){
                tmpval = lookupArabicFromRoman(roman);
                roman = roman.replace(lookupList.get(String.valueOf(tmpval)), "");
                aggregator += tmpval;
            }
        } catch (StringIndexOutOfBoundsException e){
            // invalid entries provided
            return 0;
        }
        // when no more roman numeral characters remain, we have looked up all available chunks
        logger.debug("Reverse Lookup produced " + String.valueOf(aggregator));
        return aggregator;
    }

    //////////////////
    // private methods

    /** recursion function for regressively searching the look up
     *   table for the smallest matching chunk of roman numerals
     */

    private int lookupArabicFromRoman(String romanFragment) throws StringIndexOutOfBoundsException {
        logger.debug("Search for fragment " + romanFragment);

        if (reverseLookup.containsKey(romanFragment)){
            logger.debug("key found in reverse lookup list");
            return Integer.valueOf(reverseLookup.get(romanFragment));
        } else {
            // drop the leading character and try again
            return lookupArabicFromRoman(romanFragment.substring(1));
        }

    }

    /**
     *  getRomanValuebyPower takes a given power and finds the corresponding value in the lookup table of Roman values.
     * @param power - a power of 10: 1000, 100 or 10
     * @param arabic - the number to extract the factor from
     * @return - the corresponding Roman Numeral to the factor at the specified power
     */
    private String getRomanValuebyPower(int power, int arabic){
        String powerLabel = String.valueOf(power);
        String output = "";

        if(arabic / power > 0){
            logger.debug("arabic contains a multiple of " + powerLabel);
            String cKey = String.valueOf((Math.abs(arabic/power) * power));
            logger.debug("Arabic " + powerLabel + ": " + cKey);
            output = lookupList.get(cKey);

        }
        return output;
    }

    /**
     *  buildReferenceData - loads the roman numeral reference data in from data.txt and builds a lookup list
     *  and reverse lookup list.
     */

    private void buildReferenceData(){

            String configtext;
            List<String> lookupvals = Utility.readFile("data.txt");
            //String lookupvals[] = configtext.split("[\n\r]");
            //String set[];
        String pair="";
        String set[];
        ListIterator<String> listit = lookupvals.listIterator();
            while( listit.hasNext()){
                // util.debug("importing roman numeral settings :" + pair);
                pair = (String)listit.next();
                set = pair.split(",");
                lookupList.put(set[0],set[1]);
            }


        // automatically build the reverse lookup list
        for (Object o : lookupList.entrySet()) {
            Map.Entry pair2 = (Map.Entry) o;
            reverseLookup.put((String) pair2.getValue(), (String) pair2.getKey());
        }
    }
}
