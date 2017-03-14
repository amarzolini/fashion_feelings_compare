package amz.proto.FFC.crawlers;

import junit.framework.TestCase;
import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;
import org.elasticsearch.client.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarzolini on 12.03.2017.
 */
public class vogueCrawlerTest extends TestCase {

    protected vogueCrawler fixture;

    public void setUp() throws Exception {
        super.setUp();
        fixture = new vogueCrawler();
    }

  /* public void testMain() throws Exception{

       FashionModel result = new FashionModel();
   }*/

   /*public void testGetPicture() throws Exception {

       FashionModel result;
       String url;

       url = "http://www.vogue.fr/mode";
       result = fixture.GetPicture(url);
   }*/

   public void testMain() throws Exception{
       fixture.Main();
   }

   /*public void testWriteInElasticsearch() throws Exception{

       Response result;
       FashionModel json = new FashionModel();

       json.magazine       = "TestName";
       json.imageUrl  = "TestComponent";


       fixture.WriteInElasticsearch(json);

       //App.LOG.debug(result.getRequestLine());
   }*/

}
