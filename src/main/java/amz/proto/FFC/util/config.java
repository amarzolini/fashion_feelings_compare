package amz.proto.FFC.util;

import amz.proto.FFC.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import static amz.proto.FFC.App.*;

/**
 * Created by amarzolini 13.07.2017.
 */
public class config {


    protected boolean   debugMode = false;
    protected int       connectionTimeout = 30000;
    protected int       waitingTime = 5000;

    public config(String filename) {

        Configuration conf;

        //***
        //***  LOAD CONFIGURATION FILE
        //***
        try {

            // Get values from config files if they exists. Otherwise keep the default one

            conf = new PropertiesConfiguration(filename);

            App.LOG.debug("Reading configuration file");

            if(conf.containsKey("API_KEY")){
                API_KEY += conf.getString("API_KEY");
            }
            else{
                System.err.println("FATAL ERROR: API_KEY NOT FOUND");
                System.exit(1);
            }

        } catch (ConfigurationException e) {
            App.LOG.fatal("***** ERROR: CONFIGURATION FILE " + filename + " NOT FOUND *****");
            System.err.println("\nFATAL ERROR: CONFIGURATION FILE NOT FOUND");
            System.err.println("--> " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }


    //** GETTERS

    public boolean getDebugMode() { return debugMode; }
    public int     getConnectionTimeout() { return connectionTimeout; }
    public int     getWaitingTime() { return waitingTime; }


}
