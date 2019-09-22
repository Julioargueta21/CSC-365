import SimilarityMetric.TFIDF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ParserTesting
{
    public static void main(String[] args) throws IOException {
        // tfidf loop
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Steve_Jobs");
        urlList.add("https://en.wikipedia.org/wiki/Google");
        urlList.add("https://en.wikipedia.org/wiki/Mario");
        urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");


        Parser p1 = new Parser();
        // System.out.println( p1.getTFIDF(urlList, "apple").get("apple")); //luigi //mario //steve //the //is


        // Printing out contents of Hashmaps/tables/etc
        for (Map.Entry<String, Double> entry : p1.getTFIDF(urlList, "apple").entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
                System.out.println ("Key: " + key + " | " + " Value: "  + value);


        }

    }
}