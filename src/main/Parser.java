package src.main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class Parser {

    public static ArrayList<String> getWebPage(String urls) throws IOException {
        Document webDoc = Jsoup.connect(urls).userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");
        ArrayList<String> words = null;
        for (Element e : termsList) {
            String regularExpression = "\\W+";  //* \\s+ *//*
            words = new ArrayList<>(Arrays.asList(e.text().split(regularExpression)));
        }
        return words;


    }




 /*   public static ArrayList<String> getWebPageList(ArrayList<String> urls) throws IOException {
        ArrayList<String> words = null;
        for(String u : urls){
        Document webDoc = Jsoup.connect(u).userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");

        for (Element e : termsList) {
            String regularExpression = "\\s+";  //* \\s+ *//*
            words = new ArrayList<>(Arrays.asList(e.text().split(regularExpression)));
        }

        }

        return words;

    }
*/
    public static void main(String[] args) throws IOException {
        // tfidf loop
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Steve_Jobs");
        urlList.add("https://en.wikipedia.org/wiki/Mario");

        for(int i =0; i < urlList.size(); i++){

        }

      /*  Document webDoc = Jsoup.connect("https://en.wikipedia.org/wiki/Steve_Jobs").userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");
        for(Element e : termsList) {
            System.out.println(e.text());
        }*/

    }
}






