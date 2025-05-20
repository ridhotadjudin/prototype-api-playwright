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
        String baseURL = properties.getProperty("base.url");
        System.out.println("Base URL : " + baseURL);
        return baseURL;
    }

    public static int getApiTimeout() {
        int timeout = Integer.parseInt(properties.getProperty("api.timeout"));
        System.out.println("Get API Timeout");
        return timeout;
    }
}
