package Assignment1.Core;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableOld {
    private LinkedList<Word>[] hTable;
    private int count;
    private int size;

    public HashTableOld() {
        int count = 0;
        hTable = new LinkedList[16];

        for (int i = 0; i < hTable.length; i++) {
            hTable[i] = new LinkedList<>();
        }

    }

    public Double get(String word) {
        int hash = word.hashCode(); // Hash codes Are negative Numbers
        int absHash = Math.abs(hash); // therefore with make them positive
        int tableI = absHash & (hTable.length - 1);
        //System.out.println("Table I = " + absHash + " & " + hTable.length + " = " + tableI);
        LinkedList<Word> linkedList = hTable[tableI];

        for (int i = 0; i < linkedList.size(); i++) {
            Word w = linkedList.get(i);
            if (w.word.equalsIgnoreCase(word)) {
                return w.weight;
            }
        }
        return null;
    }

    public void put(Word w) {
        resize();
        int hash = w.word.hashCode();
        int ahash = Math.abs(hash);
        int tableI = ahash & (hTable.length - 1);

        hTable[tableI].add(w);
        count++;

    }

    private ArrayList<Word> getAll() {
        ArrayList<Word> wordList = new ArrayList<>();
        for (int i = 0; i < hTable.length; i++) {
            LinkedList<Word> linkedList = hTable[i];
            for (int j = 0; j < linkedList.size(); j++) {
                Word w = linkedList.get(j);
                if (w.word != null) {
                    wordList.add(w);
                }
            }
        }
        return wordList;
    }

    private void resize() {
        if (count <= .75 * hTable.length) {
            return;
        }

        LinkedList<Word>[] lw = new LinkedList[hTable.length * 2];
        for (int i = 0; i < lw.length; i++) {
            lw[i] = new LinkedList<>();
        }
        ArrayList<Word> alw = getAll();
        for (int i = 0; i < alw.size(); i++) {

            Word w = alw.get(i);
            int hash = w.word.hashCode();
            int ahash = Math.abs(hash);
            int tableI = ahash & (lw.length - 1);
            lw[tableI].add(w);
        }
        hTable = lw;

        System.out.println("DEBUG: Hashtable Resized");
    }

}