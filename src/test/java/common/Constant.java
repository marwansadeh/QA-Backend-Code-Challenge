package common;

import java.io.File;

public class Constant {

    // System variables
    public static final String URLs_FILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "URLs.properties";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String PARAMETER = "parameter";
    public static final String RESPONSE = "response";

    public static final class URLs {
        public static final String HOST_URI = "Host_URI";
    }

    public static final class RequestParams{
        public static final String QUERY = "query";
    }
}
