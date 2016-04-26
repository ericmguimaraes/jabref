package br.com.ufs.ds3.autogeneratekey;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.BibtexEntryTypes;

public class IntegracaoBibEntryAndBibDatabase1 {

    /**
     * Tipo: Teste de integração
     * Objetivo: testar se o objeto Bibdatabase está inserindo corretamente o objeto Bibentry
     * Estratégia: Caixa preta
     * Técnica: Partição por classes de equivalência
     * OBS: Foi necessário implementar um método para saber quantas BibEntry tem na base de dados
     **/

    public void modelo() {
        BibEntry entry = new BibEntry("someid", BibtexEntryTypes.ARTICLE);
        entry.setField("author", "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        entry.setField("year", "2013");
        entry.setField("booktitle", "Advances in neural information processing systems");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        assertTrue(db.getEntryByKey("someid") != null);
    }

    @Test
    public void classe1() {
        //BibEntry vazias
        BibEntry entry = new BibEntry();
        BibEntry entry2 = new BibEntry();
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    @Test
    public void classe2() {
        //BibEntry que só tenham o id
        BibEntry entry = new BibEntry("someid");
        BibEntry entry2 = new BibEntry("someid2");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.getEntryById("someid") != null);
        assertTrue(db.getEntryById("someid2") != null);
    }

    @Test
    public void classe3() {
        //BibEntry que só tenham os autores
        BibEntry entry = new BibEntry();
        entry.setField("author", "Mikolov, Tomas and Sutskever, Ilya and Chen, Kai and Corrado, Greg S and Dean, Jeff");
        BibEntry entry2 = new BibEntry();
        entry2.setField("author", "Author1, Author11 and Author2, Author22 and Author3, Author33");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    @Test
    public void classe4() {
        //BibEntry que só tenham os títulos
        BibEntry entry = new BibEntry();
        entry.setField("title", "Distributed representations of words and phrases and their compositionality");
        BibEntry entry2 = new BibEntry();
        entry2.setField("title", "Deep learning for natural language processing");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    @Test
    public void classe5() {
        //BibEntry que só tenham os anos
        BibEntry entry = new BibEntry();
        entry.setField("year", "2013");
        BibEntry entry2 = new BibEntry();
        entry2.setField("year", "2013");
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

}
