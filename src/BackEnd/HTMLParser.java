package BackEnd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class HTMLParser {

    public static String grabWebPage() throws IOException {
        Document webDoc = Jsoup.connect("https://en.wikipedia.org/wiki/Mario").userAgent("Mozilla").data("name", "jsoup").get();
        Elements webElements = webDoc.select("div#mw-content-text");
        //This Makes the out.file (But we need to read from a control file)
        //PrintStream fileOut = new PrintStream(new File("control.txt"));
        //System.setOut(fileOut);
        String elements = null;
        for (Element el : webElements) {
            elements = el.text();
        }
        return elements;
    }

    // Counts Amount of words in array and puts words with keys on a hashmap
    public static HashMap countStrings(String[] wordList) {
        HashMap map = new HashMap();
        Integer ONE = 1;

        for (int i = 0, n = wordList.length; i < n; i++) {
            String key = wordList[i];
            Integer frequency = (Integer) map.get(key);
            if (frequency == null) {
                frequency = ONE;
            } else {
                int value = frequency.intValue();
                frequency = value + 1;
            }
            map.put(key, frequency);

        }
        return map;
    }

    public static TreeMap sortDescending(HashMap unSortedWordMap) {
        DescendingSort comp = new DescendingSort(unSortedWordMap);
        TreeMap sortedMap = new TreeMap<>(comp);
        sortedMap.putAll(unSortedWordMap);
        return sortedMap;
    }

    public static void printArrayVertically(String[] test) {
        for (String s : test) {
            System.out.println(s);
        }
    }

    public static void printListVertically(List<Entry<String, Long>> listEntry) {
        for (Entry<String, Long> entry : listEntry) {
            String key = entry.getKey();
            Long value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }

    public static List<Map.Entry<String, Long>> filterAndSort(String string, String regex) {

        //
        Map<String, Long> frequencyMap = Arrays.stream(string.split("\\s+"))
                .filter(word -> word.matches("\\b\\w{5,}\\b"))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        Comparator<Map.Entry<String, Long>> byValue = Comparator.comparing( Map.Entry::getValue);

        //
        List<Map.Entry<String, Long>> sortedByFrequency = frequencyMap.entrySet()
                .stream()
                .sorted(byValue.reversed())
                .collect( Collectors.toList());

        return sortedByFrequency;

    }


    public static void rankEntries(TreeMap tree, int rank) {

    }

    public static void run(String regex) throws IOException {
        printListVertically(filterAndSort(grabWebPage(), regex));
    }

    public static void main(String[] args) throws IOException {
        // To just grab the webpage sting
        //System.out.print(grabWebPage());

        run("[^a-zA-Z\\s]+|\\b\\w{0,4}\\b");



    }
}

