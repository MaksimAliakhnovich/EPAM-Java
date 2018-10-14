package main.java.edu.epam.selectioncommittee.service;

import main.java.edu.epam.selectioncommittee.dao.connection.CreateConnection;
import main.java.edu.epam.selectioncommittee.dao.connection.factories.ConnectionFactory;
import main.java.edu.epam.selectioncommittee.dao.connection.factories.MySqlConnectionFactory;
import main.java.edu.epam.selectioncommittee.dao.connection.factories.SqliteConnectionFactory;

import java.sql.Connection;

/**
 * Created by mascon on 11.10.2018.
 */
public class ConnectionService {
    private static ConnectionService instance;
    private String databaseType;

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public Connection getConnection() {
        ConnectionFactory connectionFactory = getConnectionFactory(databaseType);
        CreateConnection connection = connectionFactory.getConnection();
        return connection.getConnection();
    }

    private ConnectionFactory getConnectionFactory(String databaseType) {
        switch (databaseType) {
            case "mysql":
                return new MySqlConnectionFactory();
            case "sqlite":
                return new SqliteConnectionFactory();
            default:
                return null;
        }
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }
}
