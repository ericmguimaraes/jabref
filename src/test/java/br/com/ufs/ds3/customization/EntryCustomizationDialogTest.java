package br.com.ufs.ds3.customization;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.jabref.JabRefMain;
import net.sf.jabref.logic.l10n.Localization;

public class EntryCustomizationDialogTest extends AssertJSwingJUnitTestCase {

    private FrameFixture frameFixture;

    private int countPosition = 0;

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
    public void testAddEntryType() {
        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        DialogFixture d = frameFixture.dialog();
        JTextComponentFixture text = d.textBox(getActiveTextArea());
        text.enterText("TESTE_ADD_ENTRY_TYPE");
        JButtonFixture b = d.button(getActiveButton("add"));
        b.click();
        JListFixture l = d.list(getJlistStringByItemValue("TESTE_ADD_ENTRY_TYPE"));
        Assert.assertTrue(isAdded(l.target(), "TESTE_ADD_ENTRY_TYPE"));
    }

    @Test
    public void testRemoveEntryTypeAfterApply() {
        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        DialogFixture d = frameFixture.dialog();
        JTextComponentFixture text = d.textBox(getActiveTextArea());
        text.enterText("TEST_REMOVE");
        JButtonFixture addButton = d.button(getActiveButton("add"));
        addButton.click();
        JButtonFixture ApplyButton = d.button(getActiveButton("Apply"));
        ApplyButton.click();
        JListFixture l = d.list(getJlistStringByItemValue("TEST_REMOVE"));
        l.clickItem("test_remove");
        JButtonFixture removeButton = d.button(getActiveButtonByPositionAndText("remove", 1));
        removeButton.click();
        Assert.assertFalse(isAdded(l.target(), "TEST_REMOVE"));
    }

    @Test
    public void testRemoveEntryTypeBeforeApply() {
        frameFixture.menuItemWithPath("Options", Localization.lang("Customize entry types")).click();
        DialogFixture d = frameFixture.dialog();
        JTextComponentFixture text = d.textBox(getActiveTextArea());
        text.enterText("TEST_REMOVE_ENTRY_TYPE");
        JButtonFixture addButton = d.button(getActiveButton("add"));
        addButton.click();
        JListFixture l = d.list(getJlistStringByItemValue("TEST_REMOVE_ENTRY_TYPE"));
        Assert.assertTrue(isAdded(l.target(), "TEST_REMOVE_ENTRY_TYPE"));
        l.clickItem("test_remove_entry_type");
        JButtonFixture removeButton = d.button(getActiveButtonByPositionAndText("remove", 1));
        Assert.assertTrue(removeButton != null);
        removeButton.click();

        //"This entry type cannot be removed."

        JOptionPaneFixture errorMessage = d.optionPane();
        Assert.assertTrue(errorMessage != null);
        Assert.assertTrue(errorMessage.title().equals("Remove entry type"));
        errorMessage.button().click();
    }

    private GenericTypeMatcher<? extends JButton> getActiveButtonByPositionAndText(String text, int i) {
        countPosition = 0;
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {

            @Override
            protected boolean isMatching(JButton component) {
                if (component.getText().toLowerCase().equals(text.toLowerCase()) && component.isEnabled()) {
                    countPosition++;
                    if (countPosition == i) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

    private GenericTypeMatcher<JTextField> getActiveTextArea() {
        GenericTypeMatcher<JTextField> textMatcher = new GenericTypeMatcher<JTextField>(JTextField.class) {

            @Override
            protected boolean isMatching(JTextField component) {
                if(component.isEnabled()) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }

    protected boolean isAdded(JList component, String value) {
        ListModel model = component.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            Object o = model.getElementAt(i);
            if (!(o instanceof String)) {
                break;
            }
            if (value.replace(" ", "").toLowerCase().equals(((String) o).toLowerCase().replace(" ", ""))) {
                return true;
            }
        }
        return false;
    }

    private GenericTypeMatcher<JButton> getActiveButton(final String text) {
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {

            @Override
            protected boolean isMatching(JButton component) {
                if(component.getText().toLowerCase().equals(text.toLowerCase())&&component.isEnabled()) {
                    return true;
                }
                return false;
            }
        };
        return textMatcher;
    }

    private GenericTypeMatcher<javax.swing.JComboBox> getComboBoxByValue(final String value) {
        GenericTypeMatcher<javax.swing.JComboBox> textMatcher = new GenericTypeMatcher<javax.swing.JComboBox>(
                javax.swing.JComboBox.class) {

            @Override
            protected boolean isMatching(javax.swing.JComboBox textField) {
                int size = textField.getItemCount();
                for (int i = 0; i < size; i++) {
                    String fieldValue = textField.getItemAt(i).toString();
                    if (value.replace(" ", "").toLowerCase().equals(fieldValue.toLowerCase().replace(" ", ""))) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

    /*
    private GenericTypeMatcher<javax.swing.JButton> getJButtonStringByNameAndActive(final String name) {
        GenericTypeMatcher<javax.swing.JButton> nameActiveMatcher = new GenericTypeMatcher<JButton>(
                javax.swing.JButton.class) {



            @Override
            protected boolean isMatching(JButton component) {
                // TODO Auto-generated method stub

            }
        };

    }
    */

    private GenericTypeMatcher<javax.swing.JList> getJlistStringByItemValue(final String value) {
        GenericTypeMatcher<javax.swing.JList> textMatcher = new GenericTypeMatcher<javax.swing.JList>(
                javax.swing.JList.class) {

            @Override
            protected boolean isMatching(JList component) {
                ListModel model = component.getModel();

                for (int i = 0; i < model.getSize(); i++) {
                    Object o = model.getElementAt(i);
                    if (!(o instanceof String)) {
                        break;
                    }
                    if (value.replace(" ", "").toLowerCase().equals(((String) o).toLowerCase().replace(" ", ""))) {
                        return true;
                    }
                }
                return false;
            }
        };
        return textMatcher;
    }

}
