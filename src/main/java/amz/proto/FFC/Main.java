package amz.proto.FFC;


public class Main {

    static private App mApplication;
    private String[] mArgs;


    public static void main( String[] args ) throws Exception {
        String ConfigFile = "FFC.cfg";
        App application = new App(ConfigFile);
        application.init();
        application.run();
        application.stop();
    }


}
