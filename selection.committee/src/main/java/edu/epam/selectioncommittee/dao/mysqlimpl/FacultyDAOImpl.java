package edu.epam.selectioncommittee.dao.mysqlimpl;

import edu.epam.selectioncommittee.dao.FacultyDAO;
import edu.epam.selectioncommittee.entity.Faculty;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;
import edu.epam.selectioncommittee.utils.CloseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 14.10.2018.
 */
public class FacultyDAOImpl implements FacultyDAO {
    private final static String SQL_GET_ALL = ConfigurationManager.INSTANCE.getInstance().getProperty("facultyGetAll");
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;
    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    @Override
    public List<Faculty> getAll() {
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        List<Faculty> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                String name = resSet.getString("name");
                int recruitment_plan = resSet.getInt("recruitment_plan");
                list.add(new Faculty(id, name, recruitment_plan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return list;
    }
}
