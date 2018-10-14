package main.java.edu.epam.selectioncommittee.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mascon on 13.10.2018.
 */
public class ReadProperties {
    public Properties getAllProperties() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("D:\\Git\\EPAM-Java\\selection.committee\\src\\main\\resources\\application.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
