package main.java.edu.epam.selectioncommittee.dao.connection;

import java.sql.Connection;

/**
 * Created by mascon on 12.10.2018.
 */
public class MySqlConnection implements CustomConnection {
    @Override
    public Connection getConnection() {
        return ConnectorDB.getConnection();
    }
}
