package amz.proto.FFC.vision;

import amz.proto.FFC.App;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static amz.proto.FFC.App.APPNAME;
import static amz.proto.FFC.App.TARGET_URL;
import static amz.proto.FFC.App.API_KEY;


/**
 * Created by amarzolini on 12.03.2017.
 */
public class FaceDetection{

    public FaceDetection(){
    }

    public static void Main(String[] args) throws IOException{
        MakeUrlObject(args);
    }

    public static void MakeUrlObject(String[] args) throws IOException{ //Make the request
        URL serverUrl = new URL(TARGET_URL + API_KEY);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
        httpRequestBodyWriter.write
                ("{\"requests\":  [{ \"features\":  [ {\"type\": \"FACE_DETECTION\""
                        +"}], \"image\": {\"source\": { \"gcsImageUri\":"
                        +" \"gs://ffc/" + args[1] + "\"}}}]}");
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        if (httpConnection.getInputStream() == null) {
            App.LOG.debug("No stream");
            return;
        }

        App.LOG.debug(httpConnection.getInputStream());

        Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream());
        String resp = "";
        while (httpResponseScanner.hasNext()) {
            String line = httpResponseScanner.nextLine();
            resp += line;
            System.out.println(line);  //  alternatively, print the line of response
        }
        JSONObject JSONresponse = new JSONObject(resp);
        httpResponseScanner.close();
        MakeFile(args, JSONresponse); //Write the response into a JSON file (we need to specify the path into the configuration)
    }

    public static void MakeFile(String[] args, JSONObject obj) throws IOException{
        try{
            File newfile = new File(args[2]);
            newfile.createNewFile();

            FileWriter file = new FileWriter(args[2]);
            file.write(obj.toString());
            file.flush();
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
}
