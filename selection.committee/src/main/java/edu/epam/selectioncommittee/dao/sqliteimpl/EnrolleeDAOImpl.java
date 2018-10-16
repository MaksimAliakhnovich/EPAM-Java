package main.java.edu.epam.selectioncommittee.dao.sqliteimpl;

import main.java.edu.epam.selectioncommittee.dao.EnrolleeDAO;
import main.java.edu.epam.selectioncommittee.entity.Enrollee;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.utils.CloseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public class EnrolleeDAOImpl implements EnrolleeDAO {
    private final static String SQL_GET_ALL = "SELECT id, first_name, last_name, certificate_score FROM enrollee;";
    private final static String SQL_GET_BY_ID = "SELECT id, first_name, last_name, certificate_score FROM enrollee WHERE id = ?;";
    private final static String SQL_ADD = "INSERT INTO enrollee (first_name, last_name, certificate_score, passport) VALUES (?, ?, ?, ?);";
    private final static String SQL_GET_BY_PASSPORT = "SELECT id FROM enrollee WHERE passport = ?;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;


    @Override
    public List<Enrollee> getAll() {
        List<Enrollee> list = new ArrayList<>();
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                String first_name = resSet.getString("first_name");
                String last_name = resSet.getString("last_name");
                int certificate_score = resSet.getInt("certificate_score");
                list.add(new Enrollee(id, first_name, last_name, certificate_score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return list;
    }

    @Override
    public Enrollee getById(Long reqId) {
        Enrollee enrollee = null;
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_GET_BY_ID);
            prepStat.setLong(1, reqId);
            resSet = prepStat.executeQuery();
            resSet.next();
            Long id = resSet.getLong("id");
            String first_name = resSet.getString("first_name");
            String last_name = resSet.getString("last_name");
            int certificate_score = resSet.getInt("certificate_score");
            enrollee = new Enrollee(id, first_name, last_name, certificate_score);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return enrollee;
    }

    @Override
    public String add(String firstName, String lastName, int score, String passport) {
        int count = 0;
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_ADD);
            prepStat.setString(1, firstName);
            prepStat.setString(2, lastName);
            prepStat.setInt(3, score);
            prepStat.setString(4, passport);
            count = prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return count + " row(s) added successfully.";
    }

    @Override
    public Long getByPassport(String passport) {
        Long id = 0L;
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_GET_BY_PASSPORT);
            prepStat.setString(1, passport);
            resSet = prepStat.executeQuery();
            resSet.next();
            id = resSet.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return id;
    }
}
