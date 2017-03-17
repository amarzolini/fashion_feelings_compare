package amz.proto.FFC.vision;

import amz.proto.FFC.vision.FaceDetection;
import com.google.cloud.vision.spi.v1.ImageAnnotatorClient;
import junit.framework.TestCase;
import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;
import org.elasticsearch.client.Response;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarzolini on 13.03.2017.
 */
public class FaceDetectionTest extends TestCase {

    private ByteArrayOutputStream bout;
    private PrintStream out;

    protected FaceDetection fixture;

    public void setUp() throws Exception {
        super.setUp();
        bout = new ByteArrayOutputStream();
        out = new PrintStream(bout);
        fixture = new FaceDetection();
    }

   public void testMain() throws Exception{

   }
}
