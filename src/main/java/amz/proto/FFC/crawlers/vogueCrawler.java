package amz.proto.FFC.crawlers;

import amz.proto.FFC.crawlers.CommonCrawler;
import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONWriter;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * Created by amarzolini on 10.03.2017.
 */
public class vogueCrawler extends CommonCrawler {

    public vogueCrawler(){
    }

    public FashionModel GetPicture(String url) throws IOException{
        Document webPage;
        FashionModel result = new FashionModel();
        webPage = this.GetWebPage(url);

        String magazine = "Vogue";

        String imageurl = webPage.select("img.lazy").first().attr("abs:data-original").trim().toString();

        result.magazine = magazine;
        result.imageurl = imageurl;

        App.LOG.debug("Magazine: " + result.magazine);
        App.LOG.debug("URL of the picture: " + result.imageurl);

        return result;
    }

    public void DownloadPicture(String url) throws IOException{
        URL location = vogueCrawler.class.getProtectionDomain().getCodeSource().getLocation();
        try(InputStream in = new URL(url).openStream()){
            //Files.copy(in, Paths.get(location.getFile().toString() + "/out"));
            //TODO:Make a configuration file with the path
        }
    }

    public void Main(){
        String url = "http://www.vogue.fr/mode";
        FashionModel image = new FashionModel();
        //TODO: make a conf file (json)
        try {
            image = GetPicture(url);
            DownloadPicture(image.imageurl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
