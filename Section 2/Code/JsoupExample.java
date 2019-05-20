package packt.jsoupexample;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupExample {

    public static void main(String[] args) {
        try {
            File file = new File("sample.html");
            Document document = Jsoup.parse(file, "UTF-8", "");
            String title = document.title();
            out.println("Title: " + title);
            
            out.println("---Body---");
            Elements element = document.select("body");
            out.println(element.text());
            
            out.println("---Links---");
            Elements links = document.select("a[href]");
            for (Element link : links) {
                out.println(link.attr("href")
                        + " " + link.text());
            }

            out.println("---Images---");
            Elements images = document.select("img[src$=.png]");
            for (Element image : images) {
                out.println(image);
            }
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
