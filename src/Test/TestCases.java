package Test;

import com.datatribe.kata.RomanNumeralConverter;
import com.datatribe.util.*;

import static org.junit.Assert.*;
import org.junit.*;


/**
 * Created by datatribe on 10/28/2016.
 */
public class TestCases {

    // private members

    private static RomanNumeralConverter romanNumeralConverter;
    

    // public test methods


    // using Junit 4.12

    // These methods are here if needed - grabbed from sample
    // run once before any test cases
    @BeforeClass
    public static void runOnceBeforeClass(){
        // if we use any connection pools, etc. that are needed for all tests
        Utility.debug("Setting up test run...\r\n");
        romanNumeralConverter = new RomanNumeralConverter();

    }

    @AfterClass
    public static void runOnceAfterClass(){
        // tear down class members
        Utility.debug("Tearing down ...\r\n");
        romanNumeralConverter = null;
    }



    // test methods
    @Test
    public void testObjectCanConstruct(){
        Utility.debug("Test construct RomanNumeralConverter");

        assertEquals(true, romanNumeralConverter!=null);
    }



    // test cases provided by kata instructions
    @Test
    public void testProvidedCasesArabicToRoman(){
        assertEquals("I",romanNumeralConverter.arabicToRoman(1));
        Utility.debug("---");
        assertEquals("III",romanNumeralConverter.arabicToRoman(3));
        Utility.debug("---");
        assertEquals("IX",romanNumeralConverter.arabicToRoman(9));
        Utility.debug("---");
        assertEquals("MLXVI",romanNumeralConverter.arabicToRoman(1066));
        Utility.debug("---");
        assertEquals("MCMLXXXIX",romanNumeralConverter.arabicToRoman(1989));
        Utility.debug("---");
        Utility.debug("Completed specified test cases Arabic to Roman");
    }
    // test cases for reverse lookup
    @Test
    public void testProvidedCasesRomanToArabic(){
        assertEquals(1,romanNumeralConverter.romanToArabic("I"));
        Utility.debug("---");
        assertEquals(3,romanNumeralConverter.romanToArabic("III"));
        Utility.debug("---");
        assertEquals(9,romanNumeralConverter.romanToArabic("IX"));
        Utility.debug("---");
        assertEquals(1066,romanNumeralConverter.romanToArabic("MLXVI"));
        Utility.debug("---");
        assertEquals(1989,romanNumeralConverter.romanToArabic("MCMLXXXIX"));
        Utility.debug("---");
        Utility.debug("Completed specified test cases Arabic to Roman");
    }

    @Test
    public void testRomanM(){
        Utility.debug("Test Roman M value");
        assertEquals("M",romanNumeralConverter.arabicToRoman(1000));
    }


    // test composites
    @Test
    public void testCompositesMI(){
        Utility.debug("Test Roman MI");
        assertEquals("MI",romanNumeralConverter.arabicToRoman((1001)));
    }

    @Test
    public void testReverseLookupComposite(){
        Utility.debug("Testing reverse lookup with XVII");
        assertEquals(17, romanNumeralConverter.romanToArabic("XVII"));
    }

    // test with a whole value (a value found in a lookup list entry)
    @Test
    public void testReverseLookup(){
        Utility.debug("Testing reverse lookup with VII");
        assertEquals(7, romanNumeralConverter.romanToArabic("VII"));
    }


    // test round trip
    @Test
    public void testRoundTrip(){
        Utility.debug("Testing 1989 to roman to 1989");
        assertEquals(1989, romanNumeralConverter.romanToArabic(romanNumeralConverter.arabicToRoman(1989)));
    }

    // some tests to cover fault tolerances

    @Test
    public void testOutOfRangeInt(){
        Utility.debug("Testing out of range int 4000");
        assertEquals("Out of Range",romanNumeralConverter.arabicToRoman(4000));
    }

    @Test
    public void testOutOfRangeSignedInt(){
        Utility.debug("Testing out of range int -1");
        assertEquals("Out of Range",romanNumeralConverter.arabicToRoman(-1));
    }

    @Test
    public void testBadRomanNumeral(){
        // CDCDCD is not avalid Roman Numeral.  We want to make sure our
        // target method isn't going to incorrectly aggregate 400 3 times.
        assertNotEquals(1200, romanNumeralConverter.romanToArabic("CDCDCD"));
    }

    @Test
    public void testOddOrderOfValidParts(){
        // per rules, V, L and D can never be subtracted, so while this order appears to call for
        // subtracting from M, that is not possible, so this number is valid though unconventional
        assertEquals(3041, romanNumeralConverter.romanToArabic("IXLMMM"));
    }

    @Test
    public void testBadCharacters(){
        // junk text should produce zero
        assertEquals(0,romanNumeralConverter.romanToArabic("lorem ipsum delores"));
    }


    @Test
    public void testMaxRangeInt(){
        assertEquals("MMMCMXCIX", romanNumeralConverter.arabicToRoman(3999));
    }

    @Test
    public void testNearly1000(){
        // tests to make sure use of ints forces use of non decimals.
        // if the target method used float instead of int, this would erroneously return M.
        assertEquals("CMXCIX", romanNumeralConverter.arabicToRoman(999));
    }
}
