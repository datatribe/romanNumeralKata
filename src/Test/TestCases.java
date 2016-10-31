package Test;

import com.datatribe.kata.RomanNumeralConverter;

import static org.junit.Assert.*;
import org.junit.*;


/**
 * Created by datatribe on 10/28/2016.
 */
public class TestCases {

    // private members
    //private static int testValue;
    private static RomanNumeralConverter romanNumeralConverter;
    // public test methods


    // using Junit 4.12

    // These methods are here if needed - grabbed from sample
    // run once before any test cases
    @BeforeClass
    public static void runOnceBeforeClass(){
        // if we use any connection pools, etc. that are needed for all tests
        System.out.println("Setting up test run...\r\n");
        romanNumeralConverter = new RomanNumeralConverter();
        //testValue = 1666;
    }

    @AfterClass
    public static void runOnceAfterClass(){
        // tear down class members
        System.out.println("Tearing down after tests have ran.\r\n");
        romanNumeralConverter = null;
    }

    /*
    // Any operations needed before each test method, eg reinitializing class members
    @Before
    public void runBeforeTestMethod(){
        //
        System.out.println("Pre Test Case Operations...\r\n");
    }

    // Any cleanup after each test
    @After
    public void runAfterEachTest(){
        //
        System.out.println("Post Test Case Operations\r\n");
    }
    // private test globals
 */

    // test methods
    @Test
    public void testObjectCanConstruct(){
        System.out.println("Test construct RomanNumeralConverter");

        assertEquals(true, romanNumeralConverter!=null);
    }

    @Test
    public void testRomanM(){
        System.out.println("Test Roman M value");
        assertEquals("M",romanNumeralConverter.arabicToRoman(1000));
    }

    // test cases provided by kata instructions
    @Test
    public void testRomanI(){
        System.out.println("Testing Roman I value");
        assertEquals("I",romanNumeralConverter.arabicToRoman(1));
    }


    // test composites
    @Test
    public void testCompositesMI(){
        System.out.println("Test Roman MI");
        assertEquals("MI",romanNumeralConverter.arabicToRoman((1001)));
    }

    @Test
    public void testCompositesMCMLXXXIX(){
        System.out.println("Test Roman MCMLXXXIX");
        assertEquals("MCMLXXXIX",romanNumeralConverter.arabicToRoman(1989));
    }

    @Test
    public void testCompositesMLXVI(){
        System.out.println("Test Roman MLXVI");
        assertEquals("MLXVI", romanNumeralConverter.arabicToRoman(1066));
    }

    // test cases for reverse lookup

    // test with a whole value (a value found wholely in a lookup list entry
    @Test
    public void testReverseLookup(){
        System.out.println("Testing reverse lookup with VII");
        assertEquals(Integer.valueOf(7), romanNumeralConverter.romanToArabic("VII"));
    }

    // test with a composite value
    @Test
    public void testReverseLookupComposite(){
        System.out.println("Testing reverse lookup with XVII");
        assertEquals(Integer.valueOf(17), romanNumeralConverter.romanToArabic("XVII"));
    }

    // test round trip
    @Test
    public void testRoundTrip(){
        System.out.println("Testing 1989 to roman to 1989");
        assertEquals(Integer.valueOf(1989), romanNumeralConverter.romanToArabic(romanNumeralConverter.arabicToRoman(1989)));
    }
}
