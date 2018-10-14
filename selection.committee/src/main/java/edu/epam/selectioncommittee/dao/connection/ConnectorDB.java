package main.java.edu.epam.selectioncommittee.dao.connection;

import main.java.edu.epam.selectioncommittee.utils.ReadProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mascon on 13.10.2018.
 */
class ConnectorDB {
    static Connection getConnection() {
        ReadProperties props = new ReadProperties();
        String driver = props.getAllProperties().getProperty("DB_DRIVER_CLASS");
        if (driver != null) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        String url = props.getAllProperties().getProperty("DB_URL");
        String username = props.getAllProperties().getProperty("DB_USERNAME");
        String password = props.getAllProperties().getProperty("DB_PASSWORD");
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
