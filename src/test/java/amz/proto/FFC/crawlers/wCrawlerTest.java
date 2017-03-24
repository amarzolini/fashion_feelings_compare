package amz.proto.FFC.crawlers;

import junit.framework.TestCase;
import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;
import org.elasticsearch.client.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarzolini on 24.03.2017.
 */
public class wCrawlerTest extends TestCase {

    protected wCrawler fixture;

    public void setUp() throws Exception {
        super.setUp();
        fixture = new wCrawler();
    }

  /* public void testMain() throws Exception{

       FashionModel result = new FashionModel();
   }*/

   public void testGetPicture() throws Exception {

       FashionModel result;
       String url;

       url = "https://subscribe.wmagazine.com/subscribe/wmagazine/105017";
       result = fixture.GetPicture(url);
   }

   /*public void testMain() throws Exception{
       fixture.Main(args);
   }*/

   /*public void testWriteInElasticsearch() throws Exception{

       Response result;
       FashionModel json = new FashionModel();

       json.magazine       = "TestName";
       json.imageUrl  = "TestComponent";


       fixture.WriteInElasticsearch(json);

       //App.LOG.debug(result.getRequestLine());
   }*/

}
