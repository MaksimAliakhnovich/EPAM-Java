package edu.epam.selectioncommittee.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mascon on 23.10.2018.
 */
public enum ConfigurationManager {
    INSTANCE;
    private Properties properties;

    ConfigurationManager() {
        this.properties = new Properties();
        try (InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream("dbConfig.properties")) {
            this.properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("dbConfig.properties not found.");
            e.printStackTrace();
        }
        try (InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream("sqlQuery.properties")) {
            this.properties.load(inputStream);
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
