package amz.proto.FFC.util;


import amz.proto.FFC.App;
import amz.proto.FFC.model.FashionModel;


import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amarzolini (based on lgradassi's work on 10.03.2017.
 */
public class CCHelper {


    public static boolean isHex(String s) {

        if (s.matches("[0-9a-f]+")) {
            return true;
        }
        return false;
    }


    public static boolean isHex(String s, int len) {

        if (s.length() != len) {
            return false;
        } else {
            return isHex(s);
        }
    }


    public static boolean isIP4Address(String s) {
        if (s == null) {
            return false;
        }
        return s.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
    }


    public static Matcher UrlMatcher(String s) {
        Pattern urlPatt = Pattern.compile("^(https?|ftp|rtmp)://(w{3}.)?(([\\w\\s?\\.#-]+\\.)*[\\w\\s?\\.#-]+)(/[^\\s]*)?.*");
        return urlPatt.matcher(s);
    }



    /**
     * @param url
     * @return The domain name of the url
     * @throws ParseException
     */
    public static String ParseFullDomainName(String url) throws ParseException {
        Matcher m = UrlMatcher(url);
        if (m.matches()) {
            return m.group(3);
        } else {
            throw new ParseException("Unknown url format", 0);
        }
    }



    /**
     * @param domainName
     * @return The IP address in long
     * @throws UnknownHostException
     */
    public static Long IP4Address(String domainName) throws UnknownHostException {

        if (!domainName.isEmpty()) {
            byte[] inet = Inet4Address.getByName(domainName).getAddress();
            return ((inet[0] & 0xFFl) << (3 * 8)) +
                    ((inet[1] & 0xFFl) << (2 * 8)) +
                    ((inet[2] & 0xFFl) << (1 * 8)) +
                    (inet[3] & 0xFFl);
        }
        throw new UnknownHostException();
    }

    public static String LongToStringIP4(long ip) {
        int[] inet = new int[]{
                (int) ((ip >>> 24) & 0xff),
                (int) ((ip >>> 16) & 0xff),
                (int) ((ip >>> 8) & 0xff),
                (int) ((ip) & 0xff)
        };

        StringBuilder val = new StringBuilder();
        for (int i = 0; i < inet.length; i++) {
            val.append(inet[i]);
            if (i < inet.length - 1) {
                val.append(".");
            }
        }
        return val.toString();
    }


    /**
     * @param date The date object.
     * @return A date string.
     * @throws ParseException
     */
    public static String DateToString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        return formatter.format(date);
    }


    /**
     * @param datetime A string containing the datetime in YYYYMMDDHHMMSS format.
     * @return A java.util.Date object.
     * @throws ParseException
     */
    public static Date StringToDateTimeUTC(String datetime) throws ParseException {

        String dateFormat = "";


        if (datetime.matches("\\b[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\b")) {
            dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        } else if (datetime.matches("\\b[0-9]{4}-[0-9]{2}-[0-9]{2}[0-9]{2}:[0-9]{2}:[0-9]{2}\\b")) {
            dateFormat = "yyyy-MM-ddHH:mm:ss";
        } else if (datetime.matches("\\b[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\b")) {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        } else if (datetime.matches("\\b[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z\\b")) {
            dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        } else if (datetime.matches("\\b[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}\\b")) {
            dateFormat = "yyyy-MM-dd'T'HH:mm";
        } else if (datetime.matches("\\b[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}\\b")) {
            dateFormat = "yyyyMMddHHmmss";
        } else if (datetime.matches("\\b[0-9]{2}-[a-zA-Z]{3}-[0-9]{4}\\b")) {
            dateFormat = "dd-MMM-yyyy";
        } else {
            throw new ParseException("Unknown date format " + datetime, 0);
        }

        DateFormat df1 = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));

        return df1.parse(datetime);
    }


    /**
     *
     * @param title
     * @param words
     * @return
     */
    protected static boolean ContainsAllWords(String title, String words) {

        if(words==null)return false;
        if(words.length()==0) return false;

        String t = title.toLowerCase();

        for(String w : words.split(" ")) {
            if(!t.contains(w.toLowerCase())) {
                return false;
            }
        }
        return true;
    }



    /*public static void WriteResultsCSV(String ReleaseID, ReleaseModel result, FileWriter writer) {
        try {
            writer.write(ReleaseID + ";");
            writer.write(result.toString());
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FashionModel GetBestMatch(List<FashionModel> fashionmodel) {

        FashionModel result = new FashionModel();

        for(ReleaseModel c : releasenotes) {
            if(result.score<c.score) {
                result = c;
            }
        }

        return result;
    }*/


}
