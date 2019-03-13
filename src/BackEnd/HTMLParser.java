package BackEnd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import GUI.UI;

import static java.lang.Thread.sleep;

public class HTMLParser {
    private static File ctrlFile = new File( "control.txt" );
    private static Scanner scanControlFile;
    public static String text;
    public static boolean customLinkFlag;

    static {
        try {
            scanControlFile = new Scanner( ctrlFile );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void clearFiles() {
        String workingDir = System.getProperty( "user.dir" );
        File folder = new File( workingDir );
        File[] fList = folder.listFiles();

        if (fList == null) {
            throw new AssertionError();
        }
        for (File file : fList)
            if (file.getName().endsWith( ".txt" )) {
                file.delete();

            } else if (file.getName().startsWith( "control.txt" )) {

            }
    }


    public static void grabWebPage(boolean useCustomURL) throws IOException {
        // Reads From Control File
        String link = "";
        Document webDoc;
        Elements webElements;
        String elements = "";


        if (useCustomURL) {
            customLinkFlag = true;
            /// Grab Text from GUI
            link = UI.getURLTxtBox();

            webDoc = Jsoup.connect( link + "" ).userAgent( "Mozilla" ).data( "name", "jsoup" ).get();
            webElements = webDoc.select( "div#mw-content-text" );

            // To make File titles
            String[] shortLink = link.split( "^(.*[\\\\\\/])" );

            StringBuilder builder = new StringBuilder();
            for (String value : shortLink) {
                builder.append( value );
            }
            text = builder.toString();
            //This Makes the output  (Makes a output ctrlFile and cuts off System.out (GUI Depends on this being false))
            PrintStream fileOut = new PrintStream( new File( text + ".txt" ) );
            System.setOut( fileOut );
            for (Element el : webElements) {
                elements = el.text();

            }
            printListVertically( filterAndSort( elements ) );


        } else {
            int controlFileLinkLimit = 100;
            for (int amountOfLinks = 0; amountOfLinks < controlFileLinkLimit && scanControlFile.hasNextLine(); amountOfLinks++) {
                link = scanControlFile.nextLine();


                webDoc = Jsoup.connect( link ).userAgent( "Mozilla" ).data( "name", "jsoup" ).get();
                webElements = webDoc.select( "div#mw-content-text" );

                // To make File titles
                String[] shortLink = link.split( "^(.*[\\\\\\/])" );

                StringBuilder builder = new StringBuilder();
                for (String value : shortLink) {
                    builder.append( value );
                }
                text = builder.toString();
                //This Makes the output  (Makes a output ctrlFile and cuts off System.out (GUI Depends on this being false))
                PrintStream fileOut = new PrintStream( new File( text + ".txt" ) );

                System.setOut( fileOut );
                for (Element el : webElements) {
                    elements = el.text();

                }
                printListVertically( filterAndSort( elements ) );

            }
        }
    }

    // Parse and returns raw text
    private static List<Map.Entry<String, Long>> filterAndSort(String string) {
        //
        Map<String, Long> frequencyMap = Arrays.stream( string.split( "\\s+" ) )
                .filter( word -> word.matches( "\\b\\w{5,}\\b" ) )
                .collect( Collectors.groupingBy( s -> s, Collectors.counting() ) );
        Comparator<Map.Entry<String, Long>> byValue = Comparator.comparing( Map.Entry::getValue );
        //
        List<Map.Entry<String, Long>> sortedByFrequency = frequencyMap.entrySet()
                .stream()
                .sorted( byValue.reversed() )
                .collect( Collectors.toList() );
        return sortedByFrequency;
    }

    private static void printListVertically(List<Entry<String, Long>> listEntry) {

        for (Entry<String, Long> entry : listEntry) {
            String key = entry.getKey();
            Long value = entry.getValue();
            System.out.println( key + " = " + value );
        }

    }

    public static void recommendPages() throws IOException {

        if (customLinkFlag = true) {
            Scanner fileReader = new Scanner( new File( text + ".txt" ) );
            int significantWords = 5;
            int words;
            PrintStream fileOut = new PrintStream( new File( "Recommendations.txt" ) );
            System.setOut( fileOut );
            for (words = 0; fileReader.hasNextLine() == words < significantWords; words++) {
                System.out.println( "https://en.wikipedia.org/wiki/" + fileReader.nextLine().replaceAll( "[^a-zA-Z]", "" ) );

            }

        }
    }

    // Constructor
    public HTMLParser() {
    }
}

