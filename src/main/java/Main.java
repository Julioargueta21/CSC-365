import SimilarityMetric.CosineSimilarity;
import SimilarityMetric.TFIDF;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.slf4j.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Google");
       // urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");\
        urlList.add("https://simple.wikipedia.org/wiki/Google");
       // urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");
        //urlList.add("https://en.wikipedia.org/wiki/Google");
        //urlList.add("https://en.wikipedia.org/wiki/Mario");
        // urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");

        Parser[] pArr = new Parser[urlList.size()];
        HashTableOld[] htArr = new HashTableOld[urlList.size()];
        ArrayList<Double> vectorA = new ArrayList<>();
        ArrayList<Double> vectorB = new ArrayList<>();

        for (int i = 0; i < urlList.size(); i++) {
            pArr[i] = new Parser(urlList.get(i), urlList);
            htArr[i] = pArr[i].getHashTable();
            //  System.out.println("Finished Link: " + i );
        }

      /*  System.out.println("Index 0");
        for(int i =0; i < pArr[0].getWordList().size(); i++) {
            System.out.println("Key: " + pArr[0].getWordList().get(i) + " | " + "Value: " +  htArr[0].get(pArr[0].getWordList().get(i)));
        }

        System.out.println("Index 1");
        for(int i =0; i < pArr[1].getWordList().size(); i++) {
            System.out.println("Key: " + pArr[1].getWordList().get(i) + " | " + "Value: " +  htArr[1].get(pArr[1].getWordList().get(i)));
        }*/


        for (int i = 0; i < pArr[0].getWordList().size(); i++) {
            vectorA.add(htArr[0].get(pArr[0].getWordList().get(i)));
            // System.out.println("hey1");
        }

        for (int i = 0; i < pArr[1].getWordList().size(); i++) {
            vectorB.add(htArr[1].get(pArr[1].getWordList().get(i)));
            // System.out.println("hey2");
        }
        CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
        System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());


    }
}