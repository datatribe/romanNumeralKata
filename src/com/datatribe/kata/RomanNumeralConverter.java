package com.datatribe.kata;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

/**
 * Created by datatribe on 10/29/2016.
 *
 * A class to provide arabic number to roman numeral conversion, and the reverse.
 *
 * TODO: implement log4j or slf4j
 *
 */
public class RomanNumeralConverter {
    // public declarations

    // private declarations
    private Map<String, String> lookupList = new TreeMap<String, String>();
    private ArrayList<String> keys;



    // constructor
    public RomanNumeralConverter(){
       // object setup as needed

        lookupList.put("1","I");
        lookupList.put("2","II");
        lookupList.put("3","III");
        lookupList.put("4","IV");
        lookupList.put("5","V");
        lookupList.put("6","VI");
        lookupList.put("7","VII");
        lookupList.put("8","VIII");
        lookupList.put("9","IX");
        lookupList.put("10","X");
        lookupList.put("20","XX");
        lookupList.put("30","XXX");
        lookupList.put("40","XL");
        lookupList.put("50","L");
        lookupList.put("60","LX");
        lookupList.put("70","LXX");
        lookupList.put("80","LXXX");
        lookupList.put("90","XC");
        lookupList.put("100","C");
        lookupList.put("200","CC");
        lookupList.put("300","CCC");
        lookupList.put("400","CD");
        lookupList.put("500","D");
        lookupList.put("600","DC");
        lookupList.put("700","DCC");
        lookupList.put("800","DCCC");
        lookupList.put("900","CM");
        lookupList.put("1000","M");
        keys = new ArrayList<String>(lookupList.keySet());
    }

    // after mapping out the behavior of roman numerals on the white board we observe that:
    // 1-9 is always expressed the same way
    // powers of 10 are always expressed the same way
    // powers of 100 are also consistently expressed

    // based on these observations, lists of values would be a simple way to create roman numerals
    // without concerning ourselves with writing the logic to evaluate and enforce rules

    // we also observe that for 0-3, we perform addition, where as at 4, subtraction
    // then from 5-8, addition, and at 9, subtraction.  10 is our zero.

    // based on this observation, there may be a future refactoring whereby numerical functions
    // can more quickly and efficiently arrive at a solution based on this 4-1-4-1 pattern


    // public methods
    public String arabicToRoman(Integer arabic){
        String romanValue = "";

        // interrogate arabic number for thousands
        if(arabic / 1000 > 0){
            System.out.println("arabic contains a multiple of 1000");
            String mKey = String.valueOf((Math.abs(arabic/1000) * 1000));
            System.out.println("Arabic M: " + mKey);
            romanValue += lookupList.get(mKey);
            System.out.println("roman composite: " + romanValue);
        }

        // reduce arabic to hundreds
        arabic = arabic % 1000;
        System.out.println("arabic hundreds " + String.valueOf(arabic));

        // interrogate arabic number for hundreds
        if(arabic / 100 > 0){
            System.out.println("arabic contains a multiple of 100");
            String cKey = String.valueOf((Math.abs(arabic/100) * 100));
            System.out.println("Arabic C: " + cKey);
            romanValue += lookupList.get(cKey);
            System.out.println("roman composite: " + romanValue);
        }

        // reduce arabic to 10s
        arabic = arabic % 100;
        System.out.println("arabic tens " + String.valueOf(arabic));

        // interrogate arabic number for tens
        if(arabic / 10 > 0){
            System.out.println("arabic contains a multiple of 10");
            String xKey = String.valueOf((Math.abs(arabic/10) * 10));
            System.out.println("Arabic X: " + xKey);
            romanValue += lookupList.get(xKey);
            System.out.println("roman composite: " + romanValue);
        }

        // reduce arabic to ones
        arabic = arabic % 10;
        System.out.println("arabic ones " + String.valueOf(arabic));

        // interrogate arabic number for 1s
        if(arabic < 10 && arabic > 0){
            romanValue += lookupList.get(String.valueOf(arabic));
            System.out.println("roman composite: " + romanValue);
        }


        return romanValue;
    }

    public Integer romanToArabic(String roman) {
        int aggregator = 0;
        int tmpval = 0;
        while (!roman.equals("")){
            tmpval = lookupArabicFromRoman(roman);
            roman = roman.replace(lookupList.get(String.valueOf(tmpval)), "");
            aggregator += tmpval;
        }
        System.out.println("Reverse Lookup produced " + String.valueOf(aggregator));
        return Integer.valueOf(aggregator);
    }
    // private methods

    private Integer lookupArabicFromRoman(String romanFragment) {
        System.out.println("Search for fragment " + romanFragment);
        if (lookupList.containsValue(romanFragment)) {
            System.out.println("key is in map");
            for(int i = keys.size()-1; i>=0; i--){
                System.out.println("Step " + String.valueOf(i) + " has value " +lookupList.get(keys.get(i)));
                if (lookupList.get(keys.get(i)).equalsIgnoreCase(romanFragment)){
                    System.out.println("key match found");
                    return Integer.valueOf(keys.get(i));
                }
            }
        } else {
            return lookupArabicFromRoman(romanFragment.substring(1));
        }
        return 0;
    }
}
