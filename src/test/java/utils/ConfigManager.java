package utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    static Properties prop = new Properties(); //creating instance of a property class to read the properties file

    static {
        try {
            prop.load(Files.newInputStream(Paths.get("src/test/resources/config.properties")));// Load properties from the config.properties file
        } catch (Exception e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return prop.getProperty(key); //retrieves and returns the value from the properties file for the given key
    }
}
