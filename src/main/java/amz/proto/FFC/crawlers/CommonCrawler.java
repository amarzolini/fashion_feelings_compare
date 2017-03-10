package amz.proto.FFC.crawlers;

import edu.uci.ics.crawler4j.crawler.WebCrawler;
import amz.proto.FFC.App;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by amarzolini on 10.03.2017.
 */
public class CommonCrawler extends WebCrawler {
    Document GetWebPage(String url) throws IOException{
        Document result = Jsoup.connect(url).timeout(10000).get();
        App.LOG.trace(result.outerHtml());
        return result;
    }
}
