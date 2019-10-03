
package Assignment1.Core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser implements Serializable {

    private ArrayList<String> wordList;
    private ArrayList<String> urlList;
    private DocumentList<ArrayList<String>> documentList;
    private String url;


    public Parser(String url) throws IOException {
        this.url = url;
        populateWordList();

    }

    public Parser(ArrayList<String> urlList) throws IOException {
        this.urlList = urlList;
        populateDocList();
    }

    //gets the words from the webpage div
    private void populateWordList() throws IOException {
        Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();//List of words/terms
        Elements termsList = webDoc.select("div#mw-content-text");
        wordList = new ArrayList<>(Arrays.asList(termsList.text().split("[^a-zA-Z]+"))); // \\W+
    }

    private void populateDocList() throws IOException {
        DocumentList<ArrayList<String>> dList = new DocumentList<>();
        for(String url: urlList) {
            Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();//List of words/terms
            Elements termsList = webDoc.select("div#mw-content-text");
            dList.add(new ArrayList<>(Arrays.asList(termsList.text().split("[^a-zA-Z]+")))); // \\W+);
        }
        documentList = dList;
    }

    @Override
    public String toString() {
        return url.substring(30);
    }

    public ArrayList<String> getWordList(){
        return wordList;
    }

    public DocumentList<ArrayList<String>> getDocList(){
        return documentList;
    }
}