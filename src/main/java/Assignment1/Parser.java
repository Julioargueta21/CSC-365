package Assignment1;

import SimilarityMetric.TFIDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {

    ArrayList<String> urlList;
    String url;


    public Parser(String url, ArrayList urlList) {
        this.url = url;
        this.urlList = urlList;
    }

    //gets the words from the webpage div
    public ArrayList<String> getWordList() throws IOException {
        Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");

        ArrayList<String> words = null;

        /// Spilts the single string of termList into separate strings while removing punc
        for (Element e : termsList) {
            String regularExpression = "\\W+";  //* \\s+ *//*
            words = new ArrayList<>(Arrays.asList(e.text().split(regularExpression)));
        }
        return words;
    }

    public void cache() throws IOException {
        Document doc = Jsoup.connect(url).get();
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter("d://test.txt"));
        writer.write(doc.text());


    }

    public HashTable getHashTable() throws IOException {
        ArrayList<String> wordList = getWordList();
        HashTable ht = new HashTable();
        ArrayList<Word> wordObjList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            wordObjList.add(new Word(wordList.get(i), new TFIDF(getWordList(), urlList).tfidf(wordList.get(i))));
            ht.put(wordObjList.get(i));
        }
        return ht;
    }


}
