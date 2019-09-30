package Assignment1.Tests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Misc {
    public static void main(String[] args) throws IOException {
        String url = "https://www.tutorialspoint.com/about/index.htm";
        String html = Jsoup.connect(url).userAgent("Mozilla").get().html();

        Document document = Jsoup.parse(html);

        //a with href
        Element link = document.select("body").first();

        String[] words = link.text().split("[^a-zA-Z]+");
        for(String w: words) {
            System.out.println(w);
        }
    }
}
