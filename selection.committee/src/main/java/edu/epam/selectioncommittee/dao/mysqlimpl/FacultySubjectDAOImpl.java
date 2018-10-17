package main.java.edu.epam.selectioncommittee.dao.mysqlimpl;

import main.java.edu.epam.selectioncommittee.dao.FacultySubjectDAO;
import main.java.edu.epam.selectioncommittee.entity.FacultySubject;
import main.java.edu.epam.selectioncommittee.utils.DBConnectionPool;
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
public class FacultySubjectDAOImpl implements FacultySubjectDAO {
    private final static String SQL_GET_ALL_SUB_ID_BY_FAC_ID = "SELECT faculty_subject.id FROM faculty_subject " +
            "LEFT JOIN faculty ON faculty.id = faculty_subject.faculty_id " +
            "WHERE faculty_id = ?;";
    private final static String SQL_GET_ALL_SUB_NAME_BY_FAC_ID = "SELECT subject_num, subject.name FROM faculty_subject " +
            "LEFT JOIN faculty ON faculty.id = faculty_subject.faculty_id " +
            "LEFT JOIN subject ON subject.id = faculty_subject.subject_id " +
            "WHERE faculty_id = ?;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;
    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    @Override
    public List<Long> getAllSubjectsIdByFacultyId(Long facultyId) {
        List<Long> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL_SUB_ID_BY_FAC_ID);
            prepStat.setLong(1, facultyId);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("faculty_subject.id");
                list.add(id);
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
    public List<FacultySubject> getAllSubjectsNameByFacultyId(Long facultyId) {
        List<FacultySubject> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL_SUB_NAME_BY_FAC_ID);
            prepStat.setLong(1, facultyId);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                int sNum = resSet.getInt("subject_num");
                String sName = resSet.getString("subject.name");
                list.add(new FacultySubject(sNum, sName));
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
