package br.com.ufs.ds3.customization;

import javax.swing.JFrame;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.jabref.JabRefMain;
import net.sf.jabref.logic.l10n.Localization;

public class EntryCustomizationDialogTest extends AssertJSwingJUnitTestCase {

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
    public void testEntryCustomization(){
        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        DialogFixture d = frameFixture.dialog();
        JComboBoxFixture c = d.comboBox(getComboBoxByValue("book"));
        Assert.assertTrue(c != null);
    }

    private GenericTypeMatcher<javax.swing.JComboBox> getComboBoxByValue(final String value) {
        GenericTypeMatcher<javax.swing.JComboBox> textMatcher = new GenericTypeMatcher<javax.swing.JComboBox>(
                javax.swing.JComboBox.class) {

            @Override
            protected boolean isMatching(javax.swing.JComboBox textField) {
                int size = textField.getItemCount();
                for (int i = 0; i < size; i++) {
                    String fieldValue = textField.getItemAt(i).toString();
                    if (value.replace(" ", "").equals(fieldValue.replace(" ", ""))) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

}
