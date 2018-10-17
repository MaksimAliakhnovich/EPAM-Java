package main.java.edu.epam.selectioncommittee.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mascon on 17.10.2018.
 */
public class ConfigProperties {
    static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}
