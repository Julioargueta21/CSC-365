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

    public HashTableOld getHashTable() throws IOException{
        ArrayList<String> wordList = getWordList();
        HashTableOld ht = new HashTableOld();
        ArrayList<Word> wordObjList = new ArrayList<>();
        for(int i = 0; i < wordList.size(); i++) {
            wordObjList.add(new Word(wordList.get(i), new TFIDF(getWordList(),urlList).tfidf(wordList.get(i))));
            ht.put(wordObjList.get(i));
        }
        return ht;
    }
}
