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
    private HashMap<String, Double> simMap = new HashMap<>();
    private DocumentList<ArrayList<String>> dList;
    private ArrayList<HashTable> htArr;
    private ArrayList<String> urlList;
    private ArrayList<Double> vectorA;
    private ArrayList<Double> vectorB;
    private ArrayList<String> ranks;
    private ArrayList<Parser> pArr;

    public Backend() {
        vectorA = new ArrayList<>();
        vectorB = new ArrayList<>();
    }

    //inputs the url from the textBox
    public void inputUrl(String url) throws IOException {
        urlList = new ArrayList<>();
        urlList.add(0, url);
        populateUrlList();
        dList = new Parser(urlList).getDocList();
        //creates a new parser for each of the urls
        pArr = new ArrayList<>(Arrays.asList(new Parser[urlList.size()]));
        //creates a new hashtable for each of the urls
        htArr = new ArrayList<>(Arrays.asList(new HashTable[urlList.size()]));
        //sets the input to index 0
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
        ArrayList<Thread> threadList = new ArrayList<>();
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
                        //converting cached objects back into the hashtable
                        HashTable hashObject = (HashTable) inputStream.readObject();
                        System.out.println("HashTable Exists " + hashObject);
                        // Adding HashTable objects to array
                        htArr.set(index, hashObject);

                    } else {
                        // Creates Serialized Object From File Output Steam
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream( new File("HashTable" + index + ".txt")));
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
            threadList.add(thread);
        }

        //make sure every thread finished
        for (Thread current : threadList) {
            try {
                current.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void Calculate() {
        // Hard coded Vector A
        // for input url
        for(String key: pArr.get(0).getWordList()) {
            vectorA.add(htArr.get(0).get(key));
        }

        //Automatic Vector B population
        for (int urlListIndex = 1; urlListIndex < urlList.size(); urlListIndex++) {
            for (int i = 0; i < pArr.get(urlListIndex).getWordList().size(); i++) {
                //puts the weights of each url into the vector
                String key = pArr.get(urlListIndex).getWordList().get(i);
                vectorB.add(htArr.get(urlListIndex).get(key));
            }

            CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
            simMap.put(urlList.get(urlListIndex) + " ", cosineSimilarity.similarity());
            vectorB = new ArrayList<>();
        }

        ranks = new ArrayList<>();
        // print the sorted hashmap

        //sorts the ranks
         simMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                .reversed())
                .limit(5)
                .forEach(e -> ranks.add(e.toString().substring(0,e.toString().length()-12)));
    }

    public String getResult(int rank) {
        // making input index safe for the gui
        return ranks.get(rank - 1);
    }

}
