package main.java.edu.epam.selctioncomittee.connection.factories;

import main.java.edu.epam.selctioncomittee.connection.CreateConnection;
import main.java.edu.epam.selctioncomittee.connection.SqliteConnection;

/**
 * Created by mascon on 12.10.2018.
 */
public class SqliteConnectionFactory implements ConnectionFactory {
    @Override
    public CreateConnection getConnection() {
        return new SqliteConnection();
    }
}
