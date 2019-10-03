package Assignment1.Application;

import Assignment1.Core.DocumentList;
import Assignment1.Core.HTFactory;
import Assignment1.Core.HashTable;
import Assignment1.Core.Parser;
import SimilarityMetric.CosineSimilarity;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Backend {
    private ArrayList<String> urlList;
    private DocumentList<ArrayList<String>> dList;
    private ArrayList<Parser> pArr;
    private ArrayList<HashTable> htArr;
    private ArrayList<Double> vectorA;
    private ArrayList<Double> vectorB;
    private HashMap<String, Double> simMap = new HashMap<>();
    private ArrayList<String> ranks;
    private ArrayList<String> rankReset;


    public Backend() throws IOException {

        Instant start = Instant.now();

        vectorA = new ArrayList<>();
        vectorB = new ArrayList<>();


        //print the pArr to make sure it worked
        // System.out.println(pArr);
        // System.out.println(htArr)
        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time: " + interval.getSeconds());


    }

    public void inputUrl(String url) throws IOException {
        // Hard Coded Input
        urlList = new ArrayList<>();
        urlList.add(0, url);
        populateUrlList();
        dList = new Parser(urlList).getDocList();
        pArr = new ArrayList<>(Arrays.asList(new Parser[urlList.size()]));
        htArr = new ArrayList<>(Arrays.asList(new HashTable[urlList.size()]));
        pArr.set(0, new Parser(urlList.get(0)));
        htArr.set(0, new HTFactory(pArr.get(0), dList).getHashtable());
        run();
        Calculate();
    }

    private void populateUrlList() throws FileNotFoundException {
        File ctrlFile = new File("ControlFile.txt");
        Scanner sc = new Scanner(ctrlFile);
        while (sc.hasNextLine()) {
            urlList.add(sc.nextLine());
        }
        System.out.println(urlList.size() + " Documents");

    }

    private void run() {
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
    }

    private void Calculate() {
        // Hard coded Vector A
        for (int i = 0; i < pArr.get(0).getWordList().size(); i++) {
            vectorA.add(htArr.get(0).get(pArr.get(0).getWordList().get(i)));

        }

        //Automatic Vector B population
        for (int urlListIndex = 1; urlListIndex < urlList.size(); urlListIndex++) {
            for (int i = 0; i < pArr.get(urlListIndex).getWordList().size(); i++) {
                vectorB.add(htArr.get(urlListIndex).get(pArr.get(urlListIndex).getWordList().get(i)));
            }
            CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
            simMap.put(urlList.get(urlListIndex) + " ", cosineSimilarity.similarity());
            vectorB = new ArrayList<>();

        }
        ranks = rankReset;

        ranks = new ArrayList<>();
        // print the sorted hashmap

         simMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5).forEach(e -> ranks.add(e.toString()));


    }

    public String getResult(int rank) {

        return ranks.get(rank - 1);
    }

}
