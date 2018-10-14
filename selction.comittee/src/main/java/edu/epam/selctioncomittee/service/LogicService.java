package main.java.edu.epam.selctioncomittee.service;

import main.java.edu.epam.selctioncomittee.connection.CreateConnection;
import main.java.edu.epam.selctioncomittee.connection.factories.ConnectionFactory;

/**
 * Created by mascon on 11.10.2018.
 */
public class LogicService {
    private final ConnectionFactory connectionFactory;

    public LogicService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void start() {
        CreateConnection connection = connectionFactory.getConnection();
        connection.getConnection();
        // proceed with application execution...
    }
}
