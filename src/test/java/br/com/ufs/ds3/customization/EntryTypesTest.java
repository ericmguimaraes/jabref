package br.com.ufs.ds3.customization;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.jabref.JabRefMain;
import net.sf.jabref.bibtex.EntryTypes;
import net.sf.jabref.model.entry.CustomEntryType;
import net.sf.jabref.model.entry.EntryType;

public class EntryTypesTest extends AssertJSwingJUnitTestCase {

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
    public void testgetAllEntryTypes() {
        Assert.assertTrue(EntryTypes.getAllTypes().size() > 0);
    }

    @Test
    public void testGetTypeOrDefault() {
        EntryType e = EntryTypes.getTypeOrDefault("article");
        Assert.assertTrue(e != null);
        Assert.assertTrue(e.getName().equals("Article"));
        e = null;
        e = EntryTypes.getTypeOrDefault("unknownType");
        Assert.assertTrue(e != null);
    }

    @Test
    public void testGetStandardType() {
        EntryType e = EntryTypes.getStandardType("article");
        Assert.assertTrue(e != null);
        Assert.assertTrue(e.getName().equals("Article"));
        e = null;
        e = EntryTypes.getStandardType("unknownType");
        Assert.assertTrue(e == null);
    }

    @Test
    public void testAddOrModifyCustomEntryType() {
        List<String> required = new ArrayList<>();
        required.add("required field");
        CustomEntryType c = new CustomEntryType("CUSTOM ENTRY TYPE", required, null);
        EntryTypes.addOrModifyCustomEntryType(c);

        EntryType e = EntryTypes.getType("CUSTOM ENTRY TYPE");
        Assert.assertTrue(e != null);
        Assert.assertTrue(e.getName().equals("Custom entry type"));
    }

    @Test
    public void testRemoveType() {
        List<String> required = new ArrayList<>();
        required.add("required field");
        CustomEntryType c = new CustomEntryType("CUSTOM ENTRY TYPE", required, null);
        EntryTypes.addOrModifyCustomEntryType(c);

        EntryType e = EntryTypes.getType("CUSTOM ENTRY TYPE");
        Assert.assertTrue(e != null);
        Assert.assertTrue(e.getName().equals("Custom entry type"));

        EntryTypes.removeType("CUSTOM ENTRY TYPE");

        e = null;
        e = EntryTypes.getType("CUSTOM ENTRY TYPE");
        Assert.assertTrue(e == null);
    }



}
