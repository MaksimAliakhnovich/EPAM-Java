package main.java.edu.epam.selectioncommittee.dao.sqliteimpl;

import main.java.edu.epam.selectioncommittee.dao.FacultyDAO;
import main.java.edu.epam.selectioncommittee.entity.Faculty;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.utils.CloseConnection;

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
    private final static String SQL_GET_ALL = "SELECT id, name, recruitment_plan FROM faculty;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;

    @Override
    public List<Faculty> getAll() {
        List<Faculty> list = new ArrayList<>();
        try {
            conn = ConnectionService.getInstance().getConnection();
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
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return list;
    }
}