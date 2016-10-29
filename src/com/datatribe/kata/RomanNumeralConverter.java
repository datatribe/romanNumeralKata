package com.datatribe.kata;

/**
 * Created by datatribe on 10/29/2016.
 */
public class RomanNumeralConverter {
    // public declarations

    // private declarations

    // constructor
    public RomanNumeralConverter(){
       // object setup as needed
    }

    public String arabicToRoman(Integer arabicLong){
        String romanValue = "";
        if(arabicLong / 1000 > 0){
            romanValue += "M";
        }

        return romanValue;
    }

    // public methods

    // private methods
}
