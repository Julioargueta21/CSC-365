package Assignment1.Tests;


import Assignment1.DocumentList;
import Assignment1.HTFactory;
import Assignment1.HashTable;
import Assignment1.Parser;
import SimilarityMetric.CosineSimilarity;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CosineTest {
    public static void main(String[] args) throws IOException {

        Instant start = Instant.now();


        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://en.wikipedia.org/wiki/Apple_Inc.");
//        urlList.add("https://en.wikipedia.org/wiki/Nintendo");
//        urlList.add("https://en.wikipedia.org/wiki/Mario");
//        urlList.add("https://en.wikipedia.org/wiki/Linux");
//        urlList.add("https://en.wikipedia.org/wiki/Google");
//        urlList.add("https://en.wikipedia.org/wiki/Google_Play");
//        urlList.add("https://en.wikipedia.org/wiki/Space");
//        urlList.add("https://en.wikipedia.org/wiki/World_War_1");
//        urlList.add("https://en.wikipedia.org/wiki/Thinkpad");
//        urlList.add("https://en.wikipedia.org/wiki/Tiger");
//        urlList.add("https://en.wikipedia.org/wiki/Calculus");
//        urlList.add("https://en.wikipedia.org/wiki/IPhone");
//        urlList.add("https://en.wikipedia.org/wiki/IOS");
//        urlList.add("https://en.wikipedia.org/wiki/Turtle");
//        urlList.add("https://en.wikipedia.org/wiki/Arch_Linux");
//        urlList.add("https://en.wikipedia.org/wiki/Microsoft_Windows");
//        urlList.add("https://en.wikipedia.org/wiki/Trigonometric_functions");
//        urlList.add("https://en.wikipedia.org/wiki/Doug_Lea");
//        urlList.add("https://en.wikipedia.org/wiki/Shave");
        System.out.println(urlList.size());
        DocumentList<ArrayList<String>> dList = new Parser(urlList).getDocList();

        ArrayList<Parser> pArr = new ArrayList<>(Arrays.asList(new Parser[urlList.size()]));
        ArrayList<HashTable> htArr = new ArrayList<>(Arrays.asList(new HashTable[urlList.size()]));

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            final int index = i;

            //start thread and add it to our list
            Thread thread = new Thread(() -> {
                try {
                    //Adding Parser Objects to array
                    pArr.set(index, new Parser(urlList.get(index)));
                    // Adding Hashtables to array
                    htArr.set(index, new HTFactory(pArr.get(index), dList).getHashtable());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            threads.add(thread);
        }

        //make sure every thread finished
        for (Thread current : threads) {
            try {
                current.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //print the pArr to make sure it worked
        System.out.println(pArr);
        System.out.println(htArr);


        ArrayList<Double> vectorA = new ArrayList<>();
        ArrayList<Double> vectorB = new ArrayList<>();

        // Hard coded
        System.out.println("Size Of List: " + pArr.get(0).getWordList().size());
        for (int i = 0; i < pArr.get(0).getWordList().size(); i++) {
            vectorA.add(htArr.get(0).get(pArr.get(0).getWordList().get(i)));

        }

        for (int i = 0; i < pArr.get(1).getWordList().size(); i++) {
            vectorB.add(htArr.get(1).get(pArr.get(1).getWordList().get(i)));

        }

            //Automatic Vector B population
//        for (int j = 1; j < urlList.size() - 1; j++) {
//            System.out.println("Size Of List: " + pArr.get(j).getWordList().size());
//            System.out.println("Url: "+ urlList.get(j)) ;
//            for (int i = 0; i < pArr.get(j).getWordList().size(); i++) {
//                vectorB.add(htArr.get(j).get(pArr.get(j).getWordList().get(i)));
//
//            }
//
//
//            CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
//            System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());
//
//        }


        CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
        System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());

        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time: " + interval.getSeconds());

    }

}

  /*
      DecimalFormat df = new DecimalFormat("0");
      df.setMaximumFractionDigits(20);

     Parser parser  = new Parser(urlList.get(0));

     HashTable ht = new HTFactory(parser, dList).getHashtable();

     for(String key: parser.getWordList()) {
         System.out.println("Key: "  +key+ " ----> " +df.format(ht.get(key)));
     }*/