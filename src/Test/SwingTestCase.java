package Test;

/**
 * Created by domin on 11/4/2016.
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
}