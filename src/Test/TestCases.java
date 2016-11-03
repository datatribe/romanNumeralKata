package Test;

import com.datatribe.kata.RomanNumeralConverter;
import com.datatribe.util.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.apache.log4j.*;

/**
 * Created by datatribe on 10/28/2016.
 */
public class TestCases {

    // private members
    //private static int testValue;
    private static RomanNumeralConverter romanNumeralConverter;
    
    private static Logger logger = Utility.logger;
    // public test methods


    // using Junit 4.12

    // These methods are here if needed - grabbed from sample
    // run once before any test cases
    @BeforeClass
    public static void runOnceBeforeClass(){
        // if we use any connection pools, etc. that are needed for all tests
        logger.debug("Setting up test run...\r\n");
        romanNumeralConverter = new RomanNumeralConverter();
        //testValue = 1666;
    }

    @AfterClass
    public static void runOnceAfterClass(){
        // tear down class members
        logger.debug("Tearing down ...\r\n");
        romanNumeralConverter = null;
    }



    // test methods
    @Test
    public void testObjectCanConstruct(){
        logger.debug("Test construct RomanNumeralConverter");

        assertEquals(true, romanNumeralConverter!=null);
    }



    // test cases provided by kata instructions
    @Test
    public void testProvidedCasesArabicToRoman(){
        assertEquals("I",romanNumeralConverter.arabicToRoman(1));
        assertEquals("III",romanNumeralConverter.arabicToRoman(3));
        assertEquals("IX",romanNumeralConverter.arabicToRoman(9));
        assertEquals("MLXVI",romanNumeralConverter.arabicToRoman(1066));
        assertEquals("MCMLXXXIX",romanNumeralConverter.arabicToRoman(1989));
        logger.debug("Completed specified test cases Arabic to Roman");
    }
    // test cases for reverse lookup
    @Test
    public void testProvidedCasesRomanToArabic(){
        assertEquals(1,romanNumeralConverter.romanToArabic("I"));
        assertEquals(3,romanNumeralConverter.romanToArabic("III"));
        assertEquals(9,romanNumeralConverter.romanToArabic("IX"));
        assertEquals(1066,romanNumeralConverter.romanToArabic("MLXVI"));
        assertEquals(1989,romanNumeralConverter.romanToArabic("MCMLXXXIX"));
        logger.debug("Completed specified test cases Arabic to Roman");
    }

    @Test
    public void testRomanM(){
        logger.debug("Test Roman M value");
        assertEquals("M",romanNumeralConverter.arabicToRoman(1000));
    }


    // test composites
    @Test
    public void testCompositesMI(){
        logger.debug("Test Roman MI");
        assertEquals("MI",romanNumeralConverter.arabicToRoman((1001)));
    }

    @Test
    public void testReverseLookupComposite(){
        logger.debug("Testing reverse lookup with XVII");
        assertEquals(17, romanNumeralConverter.romanToArabic("XVII"));
    }

    // test with a whole value (a value found in a lookup list entry)
    @Test
    public void testReverseLookup(){
        logger.debug("Testing reverse lookup with VII");
        assertEquals(7, romanNumeralConverter.romanToArabic("VII"));
    }


    // test round trip
    @Test
    public void testRoundTrip(){
        logger.debug("Testing 1989 to roman to 1989");
        assertEquals(1989, romanNumeralConverter.romanToArabic(romanNumeralConverter.arabicToRoman(1989)));
    }

    // some tests to cover fault tolerances

    @Test
    public void testOutOfRangeInt(){
        logger.debug("Testing out of range int 4000");
        assertEquals("Out of Range",romanNumeralConverter.arabicToRoman(4000));
    }

    @Test
    public void testOutOfRangeSignedInt(){
        logger.debug("Testing out of range int -1");
        assertEquals("Out of Range",romanNumeralConverter.arabicToRoman(-1));
    }

    @Test
    public void testBadRomanNumeral(){
        // CDCDCD is not avalid Roman Numeral.  We want to make sure our
        // target method isn't going to incorrectly aggregate 400 3 times.
        assertNotEquals(1200, romanNumeralConverter.romanToArabic("CDCDCD"));
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
