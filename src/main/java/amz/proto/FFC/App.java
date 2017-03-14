package amz.proto.FFC;



import amz.proto.FFC.crawlers.*;
import amz.proto.FFC.model.FashionModel;
import amz.proto.FFC.util.*;
import amz.proto.FFC.vision.FaceDetection;
import com.drew.metadata.Face;
import com.google.cloud.vision.spi.v1.ImageAnnotatorClient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


/**
 *
 */
public class App {

    //*** CONSTANTS
    public static final String APPNAME = "Prototype - Fashion Feelings Crawler (CC)";
    public static final String VERSION = "0.1";

    //*** HELPER OBJECTSw
    public static Logger LOG = LogManager.getLogger("Application");
    private String ConfigFile = "ffc.cfg";
    public static CCConfig CONFIG;

    private ByteArrayOutputStream bout;
    private PrintStream out;



    //*** MODEL
    protected vogueCrawler vogueCrawler = new vogueCrawler();
    protected FaceDetection FaceDetection;

    /**
     *
     */
    public App() {
    }

    /**
     * Standard constructor
     *
     * @param ConfigFile The service configuration file (full path)
     */
    public App(String ConfigFile) {
        this.ConfigFile = ConfigFile;
    }


    /**
     * Initialization of the service
     */
    public void init() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        LOG.info("###### BOOTING " + APPNAME + " - Version " + VERSION + " ######");

        //***
        //***  LOAD CONFIGURATION FILE
        //***
        this.CONFIG = new CCConfig(ConfigFile);
        GoogleCredential credential = GoogleCredential.getApplicationDefault();
        /*Compute compute = new Compute.Builder
                (transport, jsonFactory, credential).build();*/

        bout = new ByteArrayOutputStream();
        out = new PrintStream(bout);
        FaceDetection = new FaceDetection(ImageAnnotatorClient.create());
    }


    /**
     * Start the service
     */
    public void run() {

        vogueCrawler.Main();
    }

    /**
     * Stop the service
     */
    public void stop() {


    }



}
