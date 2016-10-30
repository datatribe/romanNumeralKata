package com.datatribe.kata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by datatribe on 10/29/2016.
 */
public class RomanNumeralConverter {
    // public declarations

    // private declarations
    private Map<String, String> lookupList = new HashMap<String, String>();
    //private Map<String, String> tensList = new HashMap<Integer, String>();
    //private Map<String, String> hundredsList = new HashMap<Integer,String>();
    //private Map<String, String> thousandsList = new HashMap<Integer,String>();

    // constructor
    public RomanNumeralConverter(){
       // object setup as needed
        lookupList.put("1","I");


        lookupList.put("1000","M");
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



    public String arabicToRoman(Integer arabic){
        String romanValue = "";


        if(arabic / 1000 > 0){
            System.out.println("arabic contains a multiple of 1000");
            String mKey = String.valueOf((Math.abs(arabic/1000) * 1000));
            System.out.println("Arabic M: " + mKey);
            romanValue += lookupList.get(mKey);
        }

        //arabic = arabic % 1000;

        if(arabic < 10){
            romanValue += lookupList.get(String.valueOf(arabic));
        }
       // romanValue = lookupList.get(arabic);
        // next step will be to break arabic out by powers

        return romanValue;
    }

    // public methods

    // private methods
}
