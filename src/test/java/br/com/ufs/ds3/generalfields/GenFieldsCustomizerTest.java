package br.com.ufs.ds3.generalfields;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.jabref.Globals;
import net.sf.jabref.JabRefMain;
import net.sf.jabref.JabRefPreferences;


public class GenFieldsCustomizerTest extends AssertJSwingJUnitTestCase {

    private FrameFixture frameFixture;


    @BeforeClass
    public static void before() {
        FailOnThreadViolationRepaintManager.uninstall();
    }

    @Override
    protected void onSetUp() {
        ApplicationLauncher.application(JabRefMain.class).start();
        frameFixture = WindowFinder.findFrame(JFrame.class).using(robot());
    }

    @Override
    protected void onTearDown() {
        frameFixture.close();
        frameFixture = null;
    }



    @Test
    public void testGenFieldsCustomizer() {
        Assert.assertNotNull(Globals.prefs.getEntryEditorTabList());
    }

    @Test
    public void testOk_actionPerformed() {
        Assert.assertNotNull(Globals.prefs.getEntryEditorTabList());

    }

    @Test
    public void testCancel_actionPerformed() {
        Assert.assertNotNull(Globals.prefs.getEntryEditorTabList());
    }

    @Test
    public void testRevert_actionPerformed() {
        Assert.assertTrue((Globals.prefs.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + 0)));
    }

}
