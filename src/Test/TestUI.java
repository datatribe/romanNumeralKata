package Test;

import com.datatribe.kata.RomanNumeralDemo;
import com.datatribe.util.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.apache.log4j.*;

/**
 * Created by datatribe on 11/4/2016.
 */
public class TestUI {
    // private members

    private static RomanNumeralDemo romanNumeralDemo;

    private static Logger logger = Utility.logger;
    // public test methods

    @BeforeClass
    public static void runOnceBeforeClass(){
        logger.debug("Setting up test run...\r\n");
        romanNumeralDemo = new RomanNumeralDemo();

    }

    @AfterClass
    public static void runOnceAfterClass(){
        logger.debug("Tearing down ...\r\n");
        romanNumeralDemo = null;
    }

    @Test
    public void demoIsNotNull(){
        assertNotEquals(null,romanNumeralDemo);
    }

    @Test
    public void testFileReader(){
        Utility.debug("Testing file reader");
        assertEquals("test", Utility.readFile("test.txt").toString().trim());
    }

    @Test
    public void testNumberInput(){
        // I've looked at a couple of ways to test the UI automatically, still pondering best method
        // as this is new to me.
    }


}
