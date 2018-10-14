package main.java.edu.epam.selectioncommittee.dao.mysqlimpl;

import main.java.edu.epam.selectioncommittee.dao.EnrolleeDAO;
import main.java.edu.epam.selectioncommittee.entity.Enrollee;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.utils.CloseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public class EnrolleeDAOImpl implements EnrolleeDAO {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection cn = null;

    @Override
    public List<Enrollee> getAll() {
        final String SQL = "SELECT id, first_name, last_name, certificate_score FROM enrollee;";
        List<Enrollee> list = new ArrayList<>();

        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                int certificate_score = rs.getInt("certificate_score");
                list.add(new Enrollee(id, first_name, last_name, certificate_score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return list;
    }

    @Override
    public Enrollee getById(Long reqId) {
        final String SQL = "SELECT id, first_name, last_name, certificate_score FROM enrollee WHERE id = ?;";
        Enrollee enrollee = null;
        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            ps.setLong(1, reqId);
            rs = ps.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            int certificate_score = rs.getInt("certificate_score");
            enrollee = new Enrollee(id, first_name, last_name, certificate_score);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return enrollee;
    }

    @Override
    public String add(String firstName, String lastName, int score) {
        final String SQL = "INSERT INTO enrollee (first_name, last_name, certificate_score) VALUES (?, ?, ?);";
        int count = 0;
        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, score);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return count + " row(s) added successfully.";
    }

    @Override
    public Long getByFNameAndLNameAndScore(String firstName, String lastName, int score) {
        final String SQL = "SELECT id FROM enrollee WHERE first_name = ? AND last_name = ? AND certificate_score = ?;";
        Long id = 0L;
        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, score);
            rs = ps.executeQuery();
            rs.next();
            id = rs.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return id;
    }
}
