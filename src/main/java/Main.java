import SimilarityMetric.TFIDF;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Steve_Jobs");
        urlList.add("https://en.wikipedia.org/wiki/Apple_Inc.");
        //urlList.add("https://en.wikipedia.org/wiki/Google");
        //urlList.add("https://en.wikipedia.org/wiki/Mario");
       // urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");

        Parser[] pArr = new Parser[urlList.size()];
        HashTable[] htArr = new HashTable[urlList.size()];

        for (int i = 0; i < urlList.size(); i++) {
            pArr[i] = new Parser(urlList.get(i), urlList);
            htArr[i] = pArr[i].getHashTable();
        }
        ;



    }
}