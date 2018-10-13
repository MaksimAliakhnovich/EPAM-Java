package main.java.edu.epam.selctioncomittee.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by mascon on 12.10.2018.
 */
public class MySqlConnection implements CreateConnection {
    @Override
    public Connection getConnection() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("resources/application.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = props.getProperty("DB_DRIVER_CLASS");
        if (driver != null) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        String url = props.getProperty("DB_URL");
        String username = props.getProperty("DB_USERNAME");
        String password = props.getProperty("DB_PASSWORD");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Соединение успешно выполнено");
        } else {
            System.out.println("Соединение не установлено");
            System.exit(0);
        }
        return connection;
    }
}
