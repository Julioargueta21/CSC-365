import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private LinkedList<Word>[] hTable;
    private int count;
    private int size;

    public HashTable() {
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
        LinkedList<Word> linkedList = hTable[tableI];

        for(Word w : linkedList) {
           if(w.word.equalsIgnoreCase(word)) {
                return w.weight;
           }
       }
        return null;
    }

    public void put(Word w) {
        resize();

        int hash = w.word.hashCode();
        int absHash = Math.abs(hash);
        int tableI = absHash & (hTable.length - 1);

        hTable[tableI].add(w);
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


        LinkedList<Word>[] resizedList = new LinkedList[hTable.length * 2];
        for (int i = 0; i < resizedList.length; i++) {
            resizedList[i] = new LinkedList<>();
        }

        for(Word w : originalValues) {
            int hash = w.word.hashCode();
            int absHash = Math.abs(hash);
            int tableI = absHash & (resizedList.length - 1);
            resizedList[tableI].add(w);
        }
        hTable = resizedList;

       // System.out.println("DEBUG: Hashtable Resized");
    }

}