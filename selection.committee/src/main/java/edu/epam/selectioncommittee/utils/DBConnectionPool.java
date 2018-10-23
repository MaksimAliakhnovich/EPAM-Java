package main.java.edu.epam.selectioncommittee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public class DBConnectionPool {
    private String driver = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_DRIVER_CLASS");
    private String url = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_URL");
    private String username = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_USERNAME");
    private String password = ConfigurationManager.INSTANCE.getInstance().getProperty("DB_PASSWORD");
    private List<Connection> availableConnections = Collections.synchronizedList(new ArrayList<>());
    private List<Connection> usedConnections = Collections.synchronizedList(new ArrayList<>());
    private int poolMaximumActiveConnections =
            Integer.parseInt(ConfigurationManager.INSTANCE.getInstance().getProperty("poolMaximumActiveConnections"));

    // заполнение пула подключениями
    public void ConnectionPool() {
        if (driver != null) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < poolMaximumActiveConnections; i++) {
            availableConnections.add(getConnection());
        }
    }

    // создание подключения
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // выдача подключения
    public synchronized Connection getPoolConnection() throws SQLException {
        Connection connection;
        if (availableConnections.size() == 0) {
            connection = getConnection();
        } else {
            connection = availableConnections.get(availableConnections.size() - 1);
            availableConnections.remove(availableConnections.size() - 1);
        }
        usedConnections.add(connection);
        return connection;
    }

    // возврат подключения
    public synchronized void putPoolConnection(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                availableConnections.add(connection);
            } else {
                throw new NullPointerException("Connection not in the usedConnections array");
            }
        }
    }
}
