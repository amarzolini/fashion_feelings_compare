package amz.proto.FFC.model;

import amz.proto.FFC.App;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amarzolini on 10.03.2017.
 */
public class FashionModel extends CommonModel {

    public String magazine = "";
    public String imageurl = "";
    //TODO: define


    /**
     *
     */
    public FashionModel() {

    }


    /**
     *
     * @param magazine
     * @param imageurl
     */
    public FashionModel(String magazine, String imageurl, URL URL) {

        this.magazine   = magazine;
        this.imageurl   = imageurl;
    }


    /**
     *
     * @param src
     */
    public void Set(FashionModel src) {
        this.magazine   = src.magazine;
        this.imageurl   = src.imageurl;
    }




    public void Sanitize() {

        this.magazine  = this.magazine.replace("\n"," ").replace("\r"," ");
        this.imageurl  = this.imageurl.replace("\n"," ").replace("\r"," ");
    }


    /**
     *
     * @param ref
     */
    public void AddRecord(FashionModel ref) {

        this.Sanitize();
        ref.Sanitize();

        App.LOG.debug("This: "+this.toString());
        App.LOG.debug("Ref: "+ref.toString());
    }



    public String toString() {

        String ret = "";

        ret = ret + this.magazine + ";";
        ret = ret + this.imageurl + ";";

        return ret;
    }

}
