package com.codename1.parpar.tests;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.testing.TestRunnerComponent;
import com.codename1.parpar.tests.java.lang.*;
import com.codename1.parpar.tests.core.*;
import com.codename1.ui.TestComponent;
import com.codename1.ui.*;
import com.codename1.ui.geom.GeneralPathTest;
import com.codename1.ui.geom.GeneralPathTest2;
import com.codename1.util.AsyncResourceTests;
import com.codename1.util.StringUtilTests;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class ParparVMTests {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        TestRunnerComponent r = new TestRunnerComponent();
        r.add(new BooleanTest(),
                new CharacterTest(),
                new ClassTest(),
                new DoubleTest(),
                new EnumTest(),
                new FloatTest(),
                new IntegerTest(),
                new LongTest(),
                new MathTest(),
                new ObjectTest(),
                new StringBuilderTest(),
                new StringTest(),
                new ThreadTest(),
                new ThrowableTest(),
                new ThrowCatchTest(),
                new AddToQueueAndWaitOffEDTDeadlockTest(),
                new ByteCodeLevelTests(),
                new DatabaseTests(),
                new DisableInvokeAndBlockTest(),
                new FileSystemTests(),
                new InvokeAndBlockTest(),
                new JavascriptTests(),
                new LayoutTests(),
                new com.codename1.ui.ParparVMTests(),
                new RoundRectBorderTest(),
                new SimpleDateFormatTests(),
                new SpanLabelTests2980(),
                new TestComponent2(),
                new TestPicker(),
                new URITests(),
                new GeneralPathTest(),
                new GeneralPathTest2(),
                new AsyncResourceTests(),
                new StringUtilTests()
        
        );
        r.showForm();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
