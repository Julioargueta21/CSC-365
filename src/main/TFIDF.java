package src.main;

import java.io.IOException;
import java.util.ArrayList;


public class TFIDF {

    // Instance Variables
    private ArrayList<String> doc;
    private ArrayList<String> dList;

    // Constructor
    public TFIDF(ArrayList<String> url, ArrayList<String> urlList) {
        this.doc = url;
        this.dList = urlList;
    }

    public double tf(String word) {
        int counter = 0;
        for (String term : doc) {
           // System.out.println(term);
            if (term.equalsIgnoreCase(word)) {
                counter++;
            }
        }
        // System.out.println("Occurrences of same term: " + counter);
        // System.out.println( "Terms in document: " +  (double)doc.size());
        double tfAnswer = (double) counter / (double) doc.size();
        return tfAnswer;
    }

    public double idf(String word) throws IOException {
        double docsContainingTerm = 0;
        for (String url : this.dList) {
            ArrayList<String> words = Parser.getWebPage(url);
            for (String w : words) {
                if (w.equalsIgnoreCase(word)) {
                    docsContainingTerm++;
                    break;
                }
            }
        }
        return Math.log10((double) dList.size() / docsContainingTerm);
    }


    public double tfidf(String term) throws IOException {
        return tf(term) * idf(term);

    }
}
