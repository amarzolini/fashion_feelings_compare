package amz.proto.FFC;



import amz.proto.FFC.crawlers.*;
import amz.proto.FFC.model.FashionModel;
import amz.proto.FFC.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    //*** MODEL
    protected vogueCrawler vogueCrawler = new vogueCrawler();


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
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        LOG.info("###### BOOTING " + APPNAME + " - Version " + VERSION + " ######");

        //***
        //***  LOAD CONFIGURATION FILE
        //***
        this.CONFIG = new CCConfig(ConfigFile);

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
