package SimilarityMetric;

import Assignment1.Core.DocumentList;

import java.io.IOException;
import java.util.ArrayList;


public class TFIDF {

    // Instance Variables
    private ArrayList<String> doc;
    private DocumentList<ArrayList<String>> dList;
    private int docsContainingTerm;
    private int wordFrequency;


    // Constructor
    public TFIDF(ArrayList<String> wordList, DocumentList<ArrayList<String>> dList) {
        this.doc = wordList;
        this.dList = dList;
    }

    public double tf(String word) {
        int counter = 0;
        for (String term : doc) {
            if (term.equalsIgnoreCase(word)) {
                counter++;
            }
        }
        wordFrequency = counter;
        double tfAnswer = (double) wordFrequency / (double) doc.size();
        return tfAnswer;
    }

    public double idf(String word) {
        int counter = 0;
        // Repeats the Action of the inner loop for each document
        for (int docIndex = 0; docIndex < dList.size(); docIndex++) {
            // Checks one document for the Word then increments docsContainingTerm then breaks
            for (String term : dList.get(docIndex)) {
                if (term.equalsIgnoreCase(word)) {
                    counter++;
                    break;
                }

            }
        }
        docsContainingTerm = counter;
        return Math.log10((double) dList.size() / (double) docsContainingTerm);
    }

    public double tfidf(String term) throws IOException {
        double tfidfResult = tf(term) * idf(term);

        if (tfidfResult < 0) {
            throw new IOException("Something is wrong with math");
        } else {
            return tfidfResult;
        }
    }

    public int getDocsContainingTerm() {
        return docsContainingTerm;
    }

    //raw freq
    public int getWordFrequency() {
        return wordFrequency;
    }

    public int getDocSize() {
        return doc.size();
    }

    public int getDListSize() {
        return dList.size();
    }
}