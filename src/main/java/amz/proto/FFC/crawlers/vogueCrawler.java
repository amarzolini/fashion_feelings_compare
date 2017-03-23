package amz.proto.FFC.crawlers;

import amz.proto.FFC.crawlers.CommonCrawler;
import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;
import amz.proto.FFC.vision.FaceDetection;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by amarzolini on 10.03.2017.
 */
public class vogueCrawler extends CommonCrawler {

    public vogueCrawler(){
    }

    /**
     *
     * @param url
     */
    public FashionModel GetPicture(String url) throws IOException{
        Document webPage;
        FashionModel result = new FashionModel();
        webPage = this.GetWebPage(url);

        String magazine = "Vogue";

        String imageurl = webPage.select("div#wrapper div#wrapper_pub div.aside_promotion span.tolink img").first().attr("abs:src").trim().toString();

        result.magazine = magazine;
        result.imageurl = imageurl;

        App.LOG.debug("Magazine: " + result.magazine);
        App.LOG.debug("URL of the picture: " + result.imageurl);

        return result;
    }

    /**
     *
     * @param url
     */
    public String EncodeImage(String url) throws IOException{  //Encode the image in Base64 and return the String
        try{
            byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(new URL(url));
            return Base64.getEncoder().encodeToString(bytes);
        }
        catch(IOException e){}
        return null;
    }

    /**
     *
     * @param args
     */
    public void Main(String[] args){
        String url = "http://www.vogue.fr/mode";
        FashionModel image = new FashionModel();
        String ImageString;
        //TODO: make a conf file (json) containing url of the magazines
        try {
            image = GetPicture(url);
            if(image.imageurl != null) {
                ImageString = EncodeImage(image.imageurl);
                FaceDetection.Main(args, image, ImageString);
            }
            else{return;}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
