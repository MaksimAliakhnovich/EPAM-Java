package main.java.edu.epam.selectioncommittee.dao.connection.factories;

import main.java.edu.epam.selectioncommittee.dao.connection.CustomConnection;
import main.java.edu.epam.selectioncommittee.dao.connection.SqliteConnection;

/**
 * Created by mascon on 12.10.2018.
 */
public class SqliteConnectionFactory implements ConnectionFactory {
    @Override
    public CustomConnection getConnection() {
        return new SqliteConnection();
    }
}
