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
import java.io.StringWriter;
import java.net.InetSocketAddress;
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

        String imageurl = "";

        result.magazine = magazine;

        //TODO: get the url of the image using css selector
        result.imageurl = imageurl;

        return result;
    }

    public void Main(){
        String url = "vogue.fr";
        //TODO: make a conf file (json)
        try {
            GetPicture(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
