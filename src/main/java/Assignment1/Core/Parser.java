
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
        //makes jsoup connect to the url using Mozilla as the browser
        Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();
        //selects the div on the url
        Elements termsList = webDoc.select("div#mw-content-text");
        //creates an arrayList from the array of terms from the split method
        //regex removes anything that isn't a char. keeping capital and lower case letters
        wordList = new ArrayList<>(Arrays.asList(termsList.text().split("[^a-zA-Z]+")));
    }

    private void populateDocList() throws IOException {
        DocumentList<ArrayList<String>> dList = new DocumentList<>();
        for(String url: urlList) {
            Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();
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
        //list of words
        return wordList;
    }

    public DocumentList<ArrayList<String>> getDocList(){
        return documentList;
    }
}