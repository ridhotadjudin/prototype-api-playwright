package api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration properties", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static int getApiTimeout() {
        return Integer.parseInt(properties.getProperty("api.timeout"));
    }
}
