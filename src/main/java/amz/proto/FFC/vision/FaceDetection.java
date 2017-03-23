package amz.proto.FFC.vision;

import amz.proto.FFC.App;

import amz.proto.FFC.model.FashionModel;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
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

    /**
     *
     * @param args
     * @param FashionResult
     * @param ImageString
     */
    public static void Main(String[] args, FashionModel FashionResult, String ImageString) throws IOException{
        MakeUrlObject(args, FashionResult, ImageString);
    }

    /**
     *
     * @param args
     * @param FashionResult
     * @param ImageString
     */
    public static void MakeUrlObject(String[] args, FashionModel FashionResult, String ImageString) throws IOException{ //Make the request

        URL serverUrl = new URL(TARGET_URL + API_KEY); //Make the URL
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection; //Make the HTTP connection

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        httpRequestBodyWriter.write
                ("{\"requests\":  [{ \"features\":  [ {\"type\": \"FACE_DETECTION\""
                        +"}], \"image\": {\"content\": \"" + ImageString + "\"}}]}");
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        if (httpConnection.getInputStream() == null) {
            App.LOG.debug("No stream");
            return;
        }

        Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream());
        String resp = "";
        while (httpResponseScanner.hasNext()) { //Parse the response
            String line = httpResponseScanner.nextLine();
            resp += line;
            System.out.println(line);
        }
        JSONObject JSONresponse = new JSONObject(resp);
        httpResponseScanner.close();
        MakeFile(args, JSONresponse, FashionResult); //Write the response into a JSON file (we need to specify the path into the configuration)
    }

    /**
     *
     * @param args
     * @param FashionResult
     */
    public static void MakeFile(String[] args, JSONObject obj, FashionModel FashionResult) throws IOException{
        try{
            File newfile = new File(args[0] + FashionResult.magazine + ".json"); //Make the JSON file (we need to specify the path into the configuration)
            newfile.createNewFile();

            FileWriter file = new FileWriter(args[0] + FashionResult.magazine + ".json"); //Write into the JSON file
            file.write(obj.toString());
            file.flush();
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }

    }
}
