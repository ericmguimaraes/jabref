package br.com.ufs.ds3.bibtexpreferences;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.fixture.FrameFixture;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import net.sf.jabref.JabRefMain;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.logic.labelPattern.GlobalLabelPattern;

public class BibTeXPreferencesTest extends AssertJSwingJUnitTestCase {

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
    public void addBibTeXLabelPatternTest() {
        GlobalLabelPattern addpattern = new GlobalLabelPattern();
        JabRefPreferences JRprefs = JabRefPreferences.getInstance();
        Vector<String> labels = new Vector<>();
        labels.add("author");
        labels.add("story");
        labels.add("name");
        addpattern.addLabelPattern("LABEL_KEY_TEST",
                "[" + labels.elementAt(0) + "][" + labels.elementAt(1) + "][" + labels.elementAt(2) + "]");

        JRprefs.putKeyPattern(addpattern);
        GlobalLabelPattern getaddedpattern = JRprefs.getKeyPattern();
        List<String> pattern = getaddedpattern.getValue("LABEL_KEY_TEST");

        //verifica se Label Pattern foi adicionado
        Assert.assertTrue(pattern != null);
        //Verifica se Preferências do Label Pattern foram adicionadas
        for (int i = 0; i < labels.size(); i++) {
            Assert.assertTrue(pattern.contains(labels.elementAt(i)));
        }

    }
    /*
    @Test
    public void changeBibTeXLabelPattertTest() {
        JabRefPreferences JRprefs = JabRefPreferences.getInstance();
        GlobalLabelPattern patterns = JRprefs.getKeyPattern();
        patterns.addLabelPattern("LABEL_KEY_TEST", "LABEL_PATTERN_TEST");
        patterns.addLabelPattern("LABEL_KEY_TEST2", "LABEL_PATTERN_TEST2");
        patterns.addLabelPattern("LABEL_KEY_TEST3", "LABEL_PATTERN_TEST3");
        Set<String> keys = patterns.getAllKeys();
        for (String key : keys) {
            patterns.setDefaultValue(key);
        }
    }
    */
}
