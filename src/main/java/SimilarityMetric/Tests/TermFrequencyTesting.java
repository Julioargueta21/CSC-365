package SimilarityMetric.Tests;

import Assignment1.Core.DocumentList;
import Assignment1.Core.Parser;
import SimilarityMetric.TFIDF;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TermFrequencyTesting {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://en.wikipedia.org/wiki/Apple_Inc.");
        urlList.add("https://en.wikipedia.org/wiki/Nintendo");
        urlList.add("https://en.wikipedia.org/wiki/Mario");
        urlList.add("https://en.wikipedia.org/wiki/Linux");
        urlList.add("https://en.wikipedia.org/wiki/Google");
        urlList.add("https://en.wikipedia.org/wiki/Google_Play");
        urlList.add("https://en.wikipedia.org/wiki/Space");
        urlList.add("https://en.wikipedia.org/wiki/World_War_1");
        urlList.add("https://en.wikipedia.org/wiki/Thinkpad");
        urlList.add("https://en.wikipedia.org/wiki/Tiger");
        urlList.add("https://en.wikipedia.org/wiki/Calculus");
        urlList.add("https://en.wikipedia.org/wiki/IPhone");
        urlList.add("https://en.wikipedia.org/wiki/IOS");
        urlList.add("https://en.wikipedia.org/wiki/Turtle");
        urlList.add("https://en.wikipedia.org/wiki/Arch_Linux");
        urlList.add("https://en.wikipedia.org/wiki/Microsoft_Windows");
        urlList.add("https://en.wikipedia.org/wiki/Trigonometric_functions");
        urlList.add("https://en.wikipedia.org/wiki/Doug_Lea");
        urlList.add("https://en.wikipedia.org/wiki/Shave");


        Parser dListParser = new Parser(urlList);
        DocumentList<ArrayList<String>> dList = dListParser.getDocList();

        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(8);

        String term = "mario";

        for (int i = 0; i < urlList.size(); i++) {
            System.out.println("===== Metric " + (i+1) + " ===");
            System.out.println(urlList.get(i));
            Parser parser = new Parser(urlList.get(i));
            TFIDF metric = new TFIDF(parser.getWordList(), dList);
            System.out.println("tf: " + df.format(metric.tf(term)));
            System.out.println("idf: " + df.format(metric.idf(term)));
            System.out.println("tfidf: " + df.format(metric.tfidf(term)));
            System.out.println("Docs Containing Term: " + df.format(metric.getDocsContainingTerm()));
            System.out.println("Word Freq: " + df.format(metric.getWordFrequency()));
            System.out.println("Doc Size: " + df.format(metric.getDocSize()));
            System.out.println("Doc List Size: " + df.format(metric.getDListSize()));

        }
    }

}