package framework;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties PROPS = new Properties();

    static {

        try(InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                PROPS.load(in);
            } else {
                throw new IllegalStateException("config.properties file not found in classpath");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

private Config() {

}

public static String get(String key) {
        String value = PROPS.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Key '" + key + "' not found in config.properties");
        }
        return value.trim();
    }


}
