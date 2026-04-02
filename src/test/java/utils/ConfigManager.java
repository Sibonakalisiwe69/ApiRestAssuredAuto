package utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    static Properties prop = new Properties();

    static {
        try {
            prop.load(Files.newInputStream(Paths.get("src/test/resources/config.properties")));
        } catch (Exception e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
