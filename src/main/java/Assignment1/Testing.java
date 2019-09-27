package Assignment1;

import java.io.IOException;
import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) throws IOException {

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Google");
       // urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");\
        //urlList.add("https://simple.wikipedia.org/wiki/Google");
        urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");
        //urlList.add("https://en.wikipedia.org/wiki/Google");
        //urlList.add("https://en.wikipedia.org/wiki/Mario");
        // urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");

       /* Assignment1.Parser[] pArr = new Assignment1.Parser[urlList.size()];
        Assignment1.HashTable[] htArr = new Assignment1.HashTable[urlList.size()];
        ArrayList<Double> vectorA = new ArrayList<>();
        ArrayList<Double> vectorB = new ArrayList<>();

        for (int i = 0; i < urlList.size(); i++) {
            pArr[i] = new Assignment1.Parser(urlList.get(i), urlList);
            htArr[i] = pArr[i].getHashTable();
            //  System.out.println("Finished Link: " + i );
        }


        for (int i = 0; i < pArr[0].getWordList().size(); i++) {
            vectorA.add(htArr[0].get(pArr[0].getWordList().get(i)));
            // System.out.println("hey1");
        }

        for (int i = 0; i < pArr[1].getWordList().size(); i++) {
            vectorB.add(htArr[1].get(pArr[1].getWordList().get(i)));
            // System.out.println("hey2");
        }*/



  /*  Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            Assignment1.Parser p1 = new Assignment1.Parser(urlList.get(0), urlList);
            try {
                Assignment1.HashTable ht = p1.getHashTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run(){
                Assignment1.Parser p2 = new Assignment1.Parser(urlList.get(1), urlList);
                try {
                    Assignment1.HashTable ht2 = p2.getHashTable();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();*/

        Parser p1 = new Parser(urlList.get(0), urlList);
            try {
                HashTable ht = p1.getHashTable();
            } catch (IOException e) {
                e.printStackTrace();
            }

        Parser p2 = new Parser(urlList.get(1), urlList);
                try {
                    HashTable ht2 = p2.getHashTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        //   CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
     //   System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());





    }
}