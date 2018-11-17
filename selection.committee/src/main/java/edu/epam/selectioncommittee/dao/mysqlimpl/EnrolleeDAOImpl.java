package edu.epam.selectioncommittee.dao.mysqlimpl;

import edu.epam.selectioncommittee.dao.EnrolleeDAO;
import edu.epam.selectioncommittee.entity.Enrollee;
import edu.epam.selectioncommittee.utils.CloseConnection;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.DBConnectionPool;

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
    private final static String SQL_GET_ALL =
            ConfigurationManager.INSTANCE.getInstance().getProperty("enrolleeGetAll");
    private final static String SQL_GET_BY_ID =
            ConfigurationManager.INSTANCE.getInstance().getProperty("enrolleeGetById");
    private final static String SQL_ADD =
            ConfigurationManager.INSTANCE.getInstance().getProperty("enrolleeAdd");
    private final static String SQL_GET_BY_PASSPORT =
            ConfigurationManager.INSTANCE.getInstance().getProperty("enrolleeGetByPassport");
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;
    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    @Override
    public List<Enrollee> getAll() {
        List<Enrollee> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                String firstName = resSet.getString("first_name");
                String lastName = resSet.getString("last_name");
                int certificateScore = resSet.getInt("certificate_score");
                String passport = resSet.getString("passport");
                list.add(new Enrollee(id, firstName, lastName, certificateScore, passport));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return list;
    }

    @Override
    public Enrollee getById(Long reqId) {
        Enrollee enrollee = null;
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_BY_ID);
            prepStat.setLong(1, reqId);
            resSet = prepStat.executeQuery();
            resSet.next();
            Long id = resSet.getLong("id");
            String first_name = resSet.getString("first_name");
            String last_name = resSet.getString("last_name");
            int certificate_score = resSet.getInt("certificate_score");
            String passport = resSet.getString("passport");
            enrollee = new Enrollee(id, first_name, last_name, certificate_score, passport);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return enrollee;
    }

    @Override
    public int add(String firstName, String lastName, int score, String passport) {
        int count = 0;
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_ADD);
            prepStat.setString(1, firstName);
            prepStat.setString(2, lastName);
            prepStat.setInt(3, score);
            prepStat.setString(4, passport);
            count = prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return count;
    }

    @Override
    public Long getByPassport(String passport) {
        Long id = 0L;
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_BY_PASSPORT);
            prepStat.setString(1, passport);
            resSet = prepStat.executeQuery();
            resSet.next();
            id = resSet.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return id;
    }
}
