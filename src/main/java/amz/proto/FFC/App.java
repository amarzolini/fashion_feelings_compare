package amz.proto.FFC;



import amz.proto.FFC.crawlers.*;
import amz.proto.FFC.model.FashionModel;
import amz.proto.FFC.util.*;
import amz.proto.FFC.vision.FaceDetection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.security.GeneralSecurityException;
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
    protected vogueCrawler vogueCrawler   = new vogueCrawler();
    protected elleCrawler elleCrawler     = new elleCrawler();
    protected graziaCrawler graziaCrawler = new graziaCrawler();
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
        if(args.length != 2){
            App.LOG.debug("Argument(s) missing");
            App.LOG.debug("Usage: <path of the json folder> <API_KEY>");
            return;
        }
        API_KEY += args[1];

        vogueCrawler.Main(args);
        elleCrawler.Main(args);
        graziaCrawler.Main(args);

        stop();
    }

    /**
     * Stop the service
     */
    public void stop() {


    }



}
