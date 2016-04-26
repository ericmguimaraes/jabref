package br.com.ufs.ds3.autogeneratekey;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.jabref.logic.labelPattern.LabelPatternUtil;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.BibtexEntryTypes;

public class AutoGenerateKeyTest {

    @Test
    public void authorTest1() {
        System.out.println("authorTest1");
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("author", "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry.setField("year", "2013");
        entry.setField("booktitle", "Advances in neural information processing systems");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        LabelPatternUtil.setDataBase(db);
        System.out.println("out: " + LabelPatternUtil.makeLabel(entry, "authors3"));
        assertTrue(!LabelPatternUtil.makeLabel(entry, "authors3").equals(""));
    }

    @Test
    public void authorTest2() {
        System.out.println("authorTest2");
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry.setField("year", "2013");
        entry.setField("booktitle", "Advances in neural information processing systems");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        LabelPatternUtil.setDataBase(db);
        System.out.println("out: " + LabelPatternUtil.makeLabel(entry, "authors3"));
        assertTrue(!LabelPatternUtil.makeLabel(entry, "authors3").equals(""));
    }

    @Test
    public void yearTest1() {
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("author", "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry.setField("booktitle", "Advances in neural information processing systems");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        LabelPatternUtil.setDataBase(db);
        assertNotSame(LabelPatternUtil.makeLabel(entry, "year"), "");
    }

    @Test
    public void yearTest2() {
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("author", "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry.setField("year", "2013");
        entry.setField("booktitle", "Advances in neural information processing systems");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        LabelPatternUtil.setDataBase(db);
        assertNotSame(LabelPatternUtil.makeLabel(entry, "year"), "");
    }

    @Test
    public void difAuthorsTest1() {
        BibEntry entry1 = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry1.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry1.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry1.setField("booktitle", "Advances in neural information processing systems");

        BibEntry entry2 = new BibEntry("someid1", BibtexEntryTypes.ARTICLE);
        entry2.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry2.setField("title", "trabalho 2");
        entry2.setField("booktitle", "Advances in neural information processing systems");

        BibDatabase db = new BibDatabase();
        db.insertEntry(entry1);
        db.insertEntry(entry2);
        LabelPatternUtil.setDataBase(db);
        assertFalse(
                LabelPatternUtil.makeLabel(entry1, "authors3").equals(LabelPatternUtil.makeLabel(entry2, "authors3")));
    }

    @Test
    public void difYearTest1() {
        BibEntry entry1 = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry1.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry1.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry1.setField("booktitle", "Advances in neural information processing systems");

        BibEntry entry2 = new BibEntry("someid1", BibtexEntryTypes.ARTICLE);
        entry2.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry2.setField("title", "trabalho 2");
        entry2.setField("booktitle", "Advances in neural information processing systems");

        BibDatabase db = new BibDatabase();
        db.insertEntry(entry1);
        db.insertEntry(entry2);
        LabelPatternUtil.setDataBase(db);
        assertFalse(LabelPatternUtil.makeLabel(entry1, "year").equals(LabelPatternUtil.makeLabel(entry2, "year")));
    }

    @Test
    public void difYearTest2() {
        BibEntry entry1 = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry1.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry1.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry1.setField("booktitle", "Advances in neural information processing systems");
        entry1.setField("year", "2010");

        BibEntry entry2 = new BibEntry("someid1", BibtexEntryTypes.ARTICLE);
        entry2.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry2.setField("title", "trabalho 2");
        entry2.setField("booktitle", "Advances in neural information processing systems");
        entry2.setField("year", "2011");

        BibDatabase db = new BibDatabase();
        db.insertEntry(entry1);
        db.insertEntry(entry2);
        LabelPatternUtil.setDataBase(db);
        assertFalse(LabelPatternUtil.makeLabel(entry1, "year").equals(LabelPatternUtil.makeLabel(entry2, "year")));
    }

    /*public static void main(String[] args) {
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("author",
                "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry.setField("title", "trabalho 1");
        entry.setField("year", "2016");
        entry.setField("translator", "Stefan Kolb");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        LabelPatternUtil.setDataBase(db);
        System.out.println(LabelPatternUtil.makeLabel(entry, "authors3") + " <--- RETORNO");
        System.out.println(LabelPatternUtil.makeLabel(entry, "year") + " <--- RETORNO");
    
    }*/

}
