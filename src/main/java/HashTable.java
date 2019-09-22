import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    LinkedList<Word>[] ht;
    int count;

    public HashTable() {
        int count = 0;
        ht = new LinkedList[16];

        for (int i = 0; i < ht.length; i++) {
            ht[i] = new LinkedList<>();
        }

    }

    public Double get(String word) {
        int hash = word.hashCode();
        int ahash = Math.abs(hash);
        int tableI = ahash & (ht.length - 1);
        LinkedList<Word> lt = ht[tableI];

        for (int i = 0; i < lt.size(); i++) {
            Word w = lt.get(i);
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
        int tableI = ahash & (ht.length - 1);

        ht[tableI].add(w);
        count++;

    }

    private ArrayList<Word> getAll() {
        ArrayList<Word> w = new ArrayList<>();
        for (int i = 0; i < ht.length; i++) {
            LinkedList<Word> llw = ht[i];
            for (int k = 0; k < llw.size(); k++) {
                Word word = llw.get(k);
                if (word.word != null) {
                    w.add(word);
                }
            }
        }
        return w;
    }

    private void resize() {
        if (count <= .75 * ht.length) {
            return;
        }

        LinkedList<Word>[] lw = new LinkedList[ht.length * 2];
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
        ht = lw;

        System.out.println("DEBUG: Hashtable Resized");
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        for(int i = 0; i < 16; i++) {
            ht.put(new Word("shit" + i, i));
        }
        for(int i = 0; i < 16; i++) {
            System.out.println(ht.get("shit" + i));
        }
    }
}