package main.java.edu.epam.selctioncomittee.connection.factories;

import main.java.edu.epam.selctioncomittee.connection.CreateConnection;

/**
 * Created by mascon on 12.10.2018.
 */
public interface ConnectionFactory {
    CreateConnection getConnection();
}
