package main.java.edu.epam.selectioncommittee.dao.connection.factories;

import main.java.edu.epam.selectioncommittee.dao.connection.CreateConnection;
import main.java.edu.epam.selectioncommittee.dao.connection.MySqlConnection;

/**
 * Created by mascon on 12.10.2018.
 */
public class MySqlConnectionFactory implements ConnectionFactory {
    @Override
    public CreateConnection getConnection() {
        return new MySqlConnection();
    }
}
