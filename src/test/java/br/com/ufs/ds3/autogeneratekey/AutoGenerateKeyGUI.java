package br.com.ufs.ds3.autogeneratekey;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.jabref.JabRefMain;
import net.sf.jabref.logic.l10n.Localization;

public class AutoGenerateKeyGUI extends AssertJSwingJUnitTestCase {

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
        /*frameFixture.close();
        frameFixture = null;*/
    }

    @Test
    public void testetesteteste() {
        frameFixture.menuItemWithPath("File", Localization.lang("New database")).click();
        frameFixture.menuItemWithPath("Options", Localization.lang("New entry")).click();
        DialogFixture d = frameFixture.dialog(Localization.lang("Customize entry types"));
        Assert.assertTrue(d != null);
    }

}
