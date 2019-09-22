package SimilarityMetric;

import java.util.ArrayList;


import java.io.IOException;


public class TFIDF {

    // Instance Variables
    private ArrayList<String> doc;
    private ArrayList<String> dList;
    private static int docsContainingTerm = 0;
    private int wordFrequency;
    private boolean containsTerm;

    // Constructor
    public TFIDF(ArrayList<String> url, ArrayList<String> urlList) {
        this.doc = url;
        this.dList = urlList;
    }

    public double tf(String word) {
        int counter =0;
        for (String term : doc) {
            //System.out.println(term);
            if (term.equalsIgnoreCase(word)) {
                counter++;
            }
        }
        wordFrequency = counter;
        double tfAnswer = (double) wordFrequency / (double) doc.size();

        return tfAnswer;
    }

    public double idf(String word) {
        for (String w : doc) {
            if (w.equalsIgnoreCase(word)) {
                if(containsTerm == false) {
                    docsContainingTerm++;
                    containsTerm = true;
                }
                break;
            }
        }
        return Math.log10((double) dList.size() / (double) docsContainingTerm);
    }


    public int getDocsContainingTerm(){
        return docsContainingTerm;
    }

    public int getWordFrequency(){
        return  wordFrequency;
    }

    public boolean getContainsTerm(){
        return containsTerm;
    }

    public double tfidf(String term) throws IOException {
        return tf(term) * idf(term);
    }
}