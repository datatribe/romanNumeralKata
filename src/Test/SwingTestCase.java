package Test;

/**
 * Created by datatribe on 11/4/2016.
 * A stub for creating more complex ui tests later.  With the closed loop on the inputs,
 * there didn't seem to be much need to create an automated test, but it's something I want
 * to explore in the future.
 */
import junit.framework.TestCase;

import javax.swing.*;
import org.junit.*;
import java.lang.reflect.InvocationTargetException;

public class SwingTestCase extends TestCase {
    private JFrame testFrame;

    protected void tearDown(  ) throws Exception {
        if (this.testFrame != null) {
            this.testFrame.dispose(  );
            this.testFrame = null;
        }
    }

    public JFrame getTestFrame(  ) {
        if (this.testFrame == null) {
            this.testFrame = new JFrame("Test");
        }
        return this.testFrame;
    }

   /* public getComponentByName(String name){

    }*/
}