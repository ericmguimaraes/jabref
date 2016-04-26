package br.com.ufs.ds3.autogeneratekey;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibEntry;

public class IntegracaoBibEntryAndBibDatabase2 {

    /**
     * Tipo: Teste de integração
     * Objetivo: testar se o objeto Bibdatabase está inserindo corretamente o objeto Bibentry
     * Estratégia: Caixa preta
     * Técnica: Partição por classes de equivalência e Teste aleatório para gerar as entradas de cada classe.
     * OBS: Foi necessário implementar um método para saber quantas BibEntry tem na base de dados
     **/

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
        String id1 = randomString(10);
        String id2 = randomString(10);
        BibEntry entry = new BibEntry(id1);
        BibEntry entry2 = new BibEntry(id2);
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.getEntryById(id1) != null);
        assertTrue(db.getEntryById(id2) != null);
    }

    @Test
    public void classe3() {
        //BibEntry que só tenham os autores
        BibEntry entry = new BibEntry();
        entry.setField("author", randomString(5) + " and " + randomString(5));
        BibEntry entry2 = new BibEntry();
        entry2.setField("author",
                randomString(5) + " and " + randomString(5) + " and " + randomString(5) + " and " + randomString(5));
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    @Test
    public void classe4() {
        //BibEntry que só tenham os títulos
        BibEntry entry = new BibEntry();
        entry.setField("title", randomString(200));
        BibEntry entry2 = new BibEntry();
        entry2.setField("title", randomString(200));
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    @Test
    public void classe5() {
        //BibEntry que só tenham os anos
        BibEntry entry = new BibEntry();
        entry.setField("year", randomString(4));
        BibEntry entry2 = new BibEntry();
        entry2.setField("year", randomString(4));
        BibDatabase db = new BibDatabase();
        db.insertEntry(entry);
        db.insertEntry(entry2);
        assertTrue(db.countBibEntry() == 2);
    }

    public String randomString(int n) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String saida = sb.toString();
        return saida;
    }
}
