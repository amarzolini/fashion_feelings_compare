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
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.Vertex;
import com.google.common.collect.ImmutableList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
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
    public static final String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate?";
    public static String API_KEY = "key=";

    //*** HELPER OBJECTS
    public static Logger LOG = LogManager.getLogger("Application");
    private String ConfigFile = "ffc.cfg";
    public static CCConfig CONFIG;

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
       // this.CONFIG = new CCConfig(ConfigFile);
    }

    /**
     * Start the service
     */
    public void run(String[] args) throws IOException, GeneralSecurityException {
        if(args.length != 4){
            App.LOG.debug("Argument(s) missing");
            App.LOG.debug("Usage: <path of the image> <name of the image> <path of the json file> <API_KEY>");
            return;
        }
        API_KEY += args[3];
        vogueCrawler.Main();
        FaceDetection.Main(args);
    }

    /**
     * Stop the service
     */
    public void stop() {


    }



}
