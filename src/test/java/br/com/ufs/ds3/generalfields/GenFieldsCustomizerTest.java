package br.com.ufs.ds3.generalfields;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
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
        //inicializando com os valores default
        Globals.prefs.updateEntryEditorTabList();
        Assert.assertTrue((Globals.prefs.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + 0)));
    }

    @Test
    public void testOk_actionPerformed() {

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 0), "General");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 0),
                "crossref;keywords;file;doi;url;comment;owner;timestamp");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 1), "Abstract");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 1), "abstract");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 2), "Review");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 2), "review");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 3), "Teste");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 3), "teste");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 4), "Teste2");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 4), "teste2");

        Globals.prefs.purgeSeries(JabRefPreferences.CUSTOM_TAB_NAME, 5);
        Globals.prefs.purgeSeries(JabRefPreferences.CUSTOM_TAB_FIELDS, 5);
        Globals.prefs.updateEntryEditorTabList();

        Assert.assertTrue((Globals.prefs.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + 4)));

    }

    @Test
    public void testCancel_actionPerformed() {

    }

    @Test
    public void testRevert_actionPerformed() {
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 0), "General");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 0),
                "crossref;keywords;file;doi;url;comment;owner;timestamp");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 1), "Abstract");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 1), "abstract");

        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_NAME + 2), "Review");
        Globals.prefs.put((JabRefPreferences.CUSTOM_TAB_FIELDS + 2), "review");

        Globals.prefs.updateEntryEditorTabList();
        Globals.prefs.purgeSeries(JabRefPreferences.CUSTOM_TAB_NAME, 3);
        Globals.prefs.purgeSeries(JabRefPreferences.CUSTOM_TAB_FIELDS, 3);

        Assert.assertTrue((Globals.prefs.hasKey(JabRefPreferences.CUSTOM_TAB_NAME + 2)));

    }

}
