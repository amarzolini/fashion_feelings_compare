package amz.proto.FFC.util;

import amz.proto.FFC.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by amarzolini (based on lgradassi's work) on 10.03.2017.
 */
public class CCConfig {


    protected boolean   debugMode = false;
    protected int       connectionTimeout = 30000;
    protected int       waitingTime = 5000;

    public CCConfig(String filename) {

        Configuration conf;

        //***
        //***  LOAD CONFIGURATION FILE
        //***
        try {

            // Get values from config files if they exists. Otherwise keep the default one

            conf = new PropertiesConfiguration(filename);

            App.LOG.debug("Reading configuration file");
            if(conf.containsKey("debug_mode")) {
                this.debugMode = conf.getBoolean("debug_mode");
                App.LOG.debug("Reading debug_mode: "+this.debugMode);
            }

            if(conf.containsKey("connection_timeout_ms")) {
                this.connectionTimeout = conf.getInt("connection_timeout_ms");
                App.LOG.debug("Reading connection timeout: "+this.connectionTimeout);
            }

            if(conf.containsKey("waiting_time_ms")) {
                this.waitingTime = conf.getInt("waiting_time_ms");
                App.LOG.debug("Reading waiting time: "+this.waitingTime);
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
