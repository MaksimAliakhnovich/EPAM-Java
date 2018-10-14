package main.java.edu.epam.selectioncommittee.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mascon on 13.10.2018.
 */
public class CloseConnection {
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection cn) {
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (ps != null) try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (cn != null) try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
