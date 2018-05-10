package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {


    public static Properties getProperties() {
        Properties properties = new Properties();

        try (InputStream input = ClassLoader.getSystemResourceAsStream("rx-java.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


}
