package Assignment1;


import Assignment1.DocumentList;
import Assignment1.HTFactory;
import Assignment1.HashTable;
import Assignment1.Parser;
import SimilarityMetric.CosineSimilarity;

import java.io.*;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(() -> {
            new UI();
        });
        t1.start();
        Instant start = Instant.now();

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Concurrent");
        urlList.add("https://en.wikipedia.org/wiki/Fire");
        urlList.add("https://en.wikipedia.org/wiki/Match");
        urlList.add("https://en.wikipedia.org/wiki/Bear_Brook_State_Park");
        urlList.add("https://en.wikipedia.org/wiki/Park");
        urlList.add("https://en.wikipedia.org/wiki/Doug_Lea");
        urlList.add("https://en.wikipedia.org/wiki/Frontwave_Credit_Union");
        urlList.add("https://en.wikipedia.org/wiki/Pet_insurance");
        urlList.add("https://en.wikipedia.org/wiki/Sarjubala_Devi");
        urlList.add("https://en.wikipedia.org/wiki/Sports");
        urlList.add("https://en.wikipedia.org/wiki/Food");
        urlList.add("https://en.wikipedia.org/wiki/Bed");
        urlList.add("https://en.wikipedia.org/wiki/Animal");
        urlList.add("https://en.wikipedia.org/wiki/Apple_Inc.");
        urlList.add("https://en.wikipedia.org/wiki/Fan");
        urlList.add("https://en.wikipedia.org/wiki/Linux");
        urlList.add("https://en.wikipedia.org/wiki/Blanket");
        urlList.add("https://en.wikipedia.org/wiki/Oswego");
        urlList.add("https://en.wikipedia.org/wiki/Pokémon");
        urlList.add("https://en.wikipedia.org/wiki/Shave");

        System.out.println(urlList.size() + " Documents");
        DocumentList<ArrayList<String>> dList = new Parser(urlList).getDocList();

        ArrayList<Parser> pArr = new ArrayList<>(Arrays.asList(new Parser[urlList.size()]));
        ArrayList<HashTable> htArr = new ArrayList<>(Arrays.asList(new HashTable[urlList.size()]));


        // Hard Coded Input
        pArr.set(0, new Parser(urlList.get(0)));
        htArr.set(0, new HTFactory(pArr.get(0), dList).getHashtable());

        // Starting at index one to ignore first index because it's hardcoded
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i < urlList.size(); i++) {
            final int index = i;
            //start thread and add it to our list
            Thread thread = new Thread(() -> {
                try {
                    //Adding Parser Objects to array
                    pArr.set(index, new Parser(urlList.get(index)));

                    //Creating Files that will be Filled Up with Serialized Hashtable Objects
                    File f = new File("HashTable" + index + ".txt");

                    if (f.exists()) {
                        // Reading Serialized Objects from Files
                        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("HashTable" + index + ".txt"));
                        HashTable hashObject = (HashTable) inputStream.readObject();
                        System.out.println("HashTable Exists " + hashObject);

                        // Adding HashTable objects to array
                        htArr.set(index, hashObject);

                    } else {
                        // Creates Serialized Object From File Output Steam
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
                        outputStream.writeObject(new HTFactory(pArr.get(index), dList).getHashtable());

                        // Reading Serialized Objects from Files
                        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("HashTable" + index + ".txt"));
                        HashTable hashObject = (HashTable) inputStream.readObject();
                        System.out.println("Saved HashTable: " + hashObject);

                        // Adding HashTable objects to array
                        htArr.set(index, hashObject);
                    }
                } catch (IOException | ClassNotFoundException e) {
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
        HashMap<String, Double> simMap = new HashMap<>();
        // Hard coded Vector A
        System.out.println("Size Of List A: " + pArr.get(0).getWordList().size());
        for (int i = 0; i < pArr.get(0).getWordList().size(); i++) {
            vectorA.add(htArr.get(0).get(pArr.get(0).getWordList().get(i)));

        }

        //Automatic Vector B population
        for (int urlListIndex = 1; urlListIndex < urlList.size(); urlListIndex++) {
            for (int i = 0; i < pArr.get(urlListIndex).getWordList().size(); i++) {
                vectorB.add(htArr.get(urlListIndex).get(pArr.get(urlListIndex).getWordList().get(i)));
            }
            CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
            simMap.put(urlList.get(urlListIndex), cosineSimilarity.similarity());
            vectorB = new ArrayList<>();

        }
        // print the sorted hashmap
        simMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(6)
                .forEach(System.out::println);

        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time: " + interval.getSeconds());


    }

}

