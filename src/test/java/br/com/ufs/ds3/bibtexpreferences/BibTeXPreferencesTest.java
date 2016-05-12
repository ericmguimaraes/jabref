package br.com.ufs.ds3.bibtexpreferences;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import net.sf.jabref.JabRefMain;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.logic.l10n.Localization;
import net.sf.jabref.logic.labelPattern.GlobalLabelPattern;

import br.com.ufs.ds3.customization.TypeMatcherHelper;

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
    public void addBibTeXKeyTest() {
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

        Assert.assertTrue(pattern != null);

    }

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

        //Verifica se Preferências do Label Pattern foram adicionadas
        for (int i = 0; i < labels.size(); i++) {
            Assert.assertTrue(pattern.contains(labels.elementAt(i)));
        }

    }

    @Test
    public void setDefaultBibTeXLabelPattertTest() {
        JabRefPreferences JRprefs = JabRefPreferences.getInstance();
        GlobalLabelPattern defaultPatterns = new GlobalLabelPattern();
        Vector<String> defaultLabels = new Vector<>();
        defaultLabels.add("Defaultauthor");
        defaultLabels.add("Defaultstory");
        defaultLabels.add("Defaultname");
        defaultPatterns.setDefaultValue(
                "[" + defaultLabels.elementAt(0) + "][" + defaultLabels.elementAt(1) + "][" + defaultLabels.elementAt(2)
                        + "]");

        Vector<String> labels = new Vector<>();
        labels.add("author");
        labels.add("story");
        labels.add("name");

        defaultPatterns.addLabelPattern("KEY",
                "[" + labels.elementAt(0) + "][" + labels.elementAt(1) + "][" + labels.elementAt(2) + "]");

        System.out.println("====================");

        List<String> defaultValues = defaultPatterns.getValue("INVALID_KEY");
        Assert.assertTrue(defaultValues != null);
        for (int i = 0; i < defaultLabels.size(); i++) {
            Assert.assertTrue(defaultValues.contains(defaultLabels.elementAt(i)));
        }

    }

    @Test
    public void addEntryTypeAndCheckLabelPatternTest() {
        TypeMatcherHelper THM = TypeMatcherHelper.getInstance();
        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        DialogFixture d = frameFixture.dialog();
        JTextComponentFixture text = d.textBox(THM.getActiveTextArea());
        text.enterText("New_entry");
        JButtonFixture addButton = d.button(THM.getActiveButton("add"));
        addButton.click();
        JButtonFixture ok = d.button(THM.getActiveButton("ok"));
        ok.click();

        frameFixture.menuItemWithPath("Options", Localization.lang("Preferences")).click();
        DialogFixture pref = frameFixture.dialog();
        JListFixture prefList = pref.list();
        prefList.clickItem("BibTeX key generator");

        JLabelFixture newentry = pref.label(THM.getLabelbyName("New_entry"));
        Assert.assertTrue(newentry != null);

        pref.close();

        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        d = frameFixture.dialog();
        text = d.textBox(THM.getActiveTextArea());
        text.enterText("Another_entry");
        addButton = d.button(THM.getActiveButton("add"));
        addButton.click();
        ok = d.button(THM.getButtonByText("OK"));
        ok.click();

        frameFixture.menuItemWithPath("Options", Localization.lang("Preferences")).click();
        pref = frameFixture.dialog();
        prefList = pref.list();
        prefList.clickItem("BibTeX key generator");

        JLabelFixture anotherentry = null;
        try {
            anotherentry = pref.label(THM.getLabelbyName("Another_entry"));
        } catch (Exception e) {
            System.out.println("Component not found");
        }
        //É esperado que aqui ocorra um erro
        Assert.assertTrue(anotherentry != null);

        pref.close();
    }

}
