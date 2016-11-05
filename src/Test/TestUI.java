package Test;

import com.datatribe.kata.RomanNumeralDemo;
import com.datatribe.util.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.apache.log4j.*;

/**
 * Created by datatribe on 11/4/2016.
 */
public class TestUI extends SwingTestCase{
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





}
