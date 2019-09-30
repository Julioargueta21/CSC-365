package Assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class HashTable {
    private LinkedList<Word>[] hTable;
    private int count;

    public HashTable() {
        count = 0;
        hTable = new LinkedList[16];
        Arrays.fill(hTable, new LinkedList<>());

    }

    public Double get(String word) {
        int tableIndex = Math.abs(word.hashCode()) & (hTable.length - 1);
        LinkedList<Word> linkedList = hTable[tableIndex];

        for (Word w : linkedList) {
            if (w.word.equalsIgnoreCase(word)) {
                return w.weight;
            }
        }
        return null;
    }

    public void put(Word w) {
        resize();

        int tableIndex = Math.abs(w.word.hashCode()) & (hTable.length - 1);
        hTable[tableIndex].add(w);
        count++;

    }

    private ArrayList<Word> getAll() {
        ArrayList<Word> wordList = new ArrayList<>();

        for (LinkedList<Word> linkedList : hTable) {
            for (Word w : linkedList) {
                if (w.word != null) {
                    wordList.add(w);
                }
            }
        }
        return wordList;
    }

    private void resize() {
        ArrayList<Word> originalValues = getAll();
        if (count <= .75 * hTable.length) {
            return;
        }

        LinkedList<Word>[] resizedList = new LinkedList[hTable.length * 2]; //


        for (int i = 0; i < resizedList.length; i++) {
            resizedList[i] = new LinkedList<>();
        }

        for (Word w : originalValues) {
            int tableIndex = Math.abs(w.word.hashCode()) & (resizedList.length - 1);
            resizedList[tableIndex].add(w);
        }
        hTable = resizedList;

        System.out.println("DEBUG: Hashtable Resized: " + "Size: " + resizedList.length + " ID: " + Thread.currentThread().getId());
    }

}