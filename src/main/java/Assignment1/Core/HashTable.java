package Assignment1.Core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class HashTable implements Serializable {
    private LinkedList<Word>[] hTable;
    private int count;

    public HashTable() {
        count = 0;
        hTable = new LinkedList[16];
        Arrays.fill(hTable, new LinkedList<>());
    }

    public Double get(String key) {
        int hashTableIndex = Math.abs(key.hashCode()) & (hTable.length - 1);
        LinkedList<Word> linkedList = hTable[hashTableIndex];

        for (Word w : linkedList) {
            if (w.word.equalsIgnoreCase(key)) {
                return w.weight;
            }
        }
        return null;
    }

    public void put(Word w) {
        resize();

        int hashTableIndex = Math.abs(w.word.hashCode()) & (hTable.length - 1);
        hTable[hashTableIndex].add(w);
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
        //if the arrayList is less than 75% full don't resize
        if (count <= .75 * hTable.length) {
            return;
        }

        //if the array list is 75% full perform this
        LinkedList<Word>[] resizedList = new LinkedList[hTable.length * 2];
        for (int i = 0; i < resizedList.length; i++) {
            resizedList[i] = new LinkedList<>();
        }

        for (Word w : originalValues) {
            int tableIndex = Math.abs(w.word.hashCode()) & (resizedList.length - 1);
            resizedList[tableIndex].add(w);
        }
        hTable = resizedList;
    }


}