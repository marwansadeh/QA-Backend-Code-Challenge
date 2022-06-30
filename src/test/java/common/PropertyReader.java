package common;

import java.io.FileInputStream;
import java.util.Properties;

public final class PropertyReader {

    private static final Properties properties = new Properties();

    static {
        initProperties();
    }


    private static void initProperties() {
        try {
            properties.load(new FileInputStream(Constant.URLs_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(String key) {
        return properties.getProperty(key);
    }

}
