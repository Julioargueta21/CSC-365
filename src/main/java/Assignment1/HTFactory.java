package Assignment1;

import SimilarityMetric.TFIDF;

import java.io.IOException;
import java.util.ArrayList;

public class HTFactory {
    Parser parser;
    DocumentList<ArrayList<String>> dList;
    HashTable ht;

    public HTFactory(Parser parser, DocumentList<ArrayList<String>> dList) {
        this.parser = parser;
        this.dList = dList;
        ht = new HashTable();
    }

    public HashTable getHashtable() throws IOException {
        ArrayList<String> wordList = parser.getWordList();
        TFIDF metric = new TFIDF(wordList, dList);

        for (int i = 0; i < wordList.size(); i++) {
            ht.put(new Word(wordList.get(i), metric.tfidf( wordList.get(i) )));
        }
        return ht;

    }

}

