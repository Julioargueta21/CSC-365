import SimilarityMetric.TFIDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;


public class Parser {

    ArrayList<String> urlList;
    String urls;
    TFIDF tfidfCalculator;

    public Parser(String url, ArrayList urlList) {
        this.urls = url;
        this.urlList = urlList;
    }

    //gets the words from the webpage div
    public ArrayList<String> getWordList() throws IOException {
        Document webDoc = Jsoup.connect(urls).userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");
        ArrayList<String> words = null;
        for (Element e : termsList) {
            String regularExpression = "\\W+";  //* \\s+ *//*
            words = new ArrayList<>(Arrays.asList(e.text().split(regularExpression)));
        }
        return words;
    }

    public HashTable getHashTable() throws IOException{
        ArrayList<String> wordlist = getWordList();
        HashTable ht = new HashTable();
        tfidfCalculator = new TFIDF(getWordList(), urlList);
        ArrayList<Word> wordObjList = new ArrayList<>();
        for(int i = 0; i < wordlist.size(); i++) {
            wordObjList.add(new Word(wordlist.get(i), tfidfCalculator.tfidf(wordlist.get(i))));
            ht.put(wordObjList.get(i));
        }

        return ht;
    }

    public void debug(String term) throws IOException{
        tfidfCalculator = new TFIDF(getWordList(), urlList);
        System.out.println("The Term: " + "(" + term + ")" + " in -> " + urls);
        System.out.println("TF: " + tfidfCalculator.tf(term));
        System.out.println("IDF: " + tfidfCalculator.idf(term));
        System.out.println("TFIDF: " + tfidfCalculator.tfidf(term));
        System.out.println("Word Frequency: " + tfidfCalculator.getWordFrequency());
        System.out.println("isTermContained?: " + tfidfCalculator.getContainsTerm());
        System.out.println("Docs Containing Term: " + tfidfCalculator.getDocsContainingTerm());
        System.out.println("----------------------------------------------");


    }
}
