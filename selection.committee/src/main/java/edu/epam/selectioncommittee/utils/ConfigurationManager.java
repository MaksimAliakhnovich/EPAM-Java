package main.java.edu.epam.selectioncommittee.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mascon on 23.10.2018.
 */
public enum ConfigurationManager {
    INSTANCE;
    private Properties properties;

    ConfigurationManager() {
        this.properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/dbConfig.properties")) {
            this.properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("dbConfig.properties not found.");
            e.printStackTrace();
        }
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/sqlQuery.properties")) {
            this.properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("sqlQuery.properties not found.");
            e.printStackTrace();
        }
    }

    public String getProperty(String propName) {
        return properties.getProperty(propName);
    }

    public ConfigurationManager getInstance() {
        return INSTANCE;
    }
}
