package main.java.edu.epam.selectioncommittee.dao.sqliteimpl;

import main.java.edu.epam.selectioncommittee.dao.RegisterDAO;
import main.java.edu.epam.selectioncommittee.entity.Register;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.utils.CloseConnection;
import main.java.edu.epam.selectioncommittee.utils.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 13.10.2018.
 */
public class RegisterDAOImpl implements RegisterDAO{
    private final static String SQL_GET_ALL = "SELECT id, enrollee_id, subject_id," +
            " subject_score, faculty_id FROM register;";
    private final static String SQL_ADD = "INSERT INTO register (enrollee_id, subject_id, subject_score, faculty_id)" +
            " VALUES (?, ?, ?, ?);";
    private final static String SQL_GET_RECRUITMENT_PLAN_BY_FAC_ID = "SELECT recruitment_plan FROM faculty WHERE id = ?";
    private final static String SQL_GET_TOP_ENROLLEE = "SELECT enrollee_id, enrollee.first_name, enrollee.last_name, " +
            "SUM(subject_score) + enrollee.certificate_score total FROM register " +
            "LEFT JOIN enrollee ON enrollee.id = register.enrollee_id " +
            "WHERE faculty_id = ? " +
            "GROUP BY enrollee_id " +
            "ORDER BY total DESC " +
            "LIMIT 0, ?;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;

    @Override
    public List<Register> getAll() {
        List<Register> list = new ArrayList<>();
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                Long enrolleeId = resSet.getLong("enrollee_id");
                Long subjectId = resSet.getLong("subject_id");
                int subjectScore = resSet.getInt("subject_score");
                Long facultyId = resSet.getLong("faculty_id");
                list.add(new Register(id, enrolleeId, subjectId, subjectScore, facultyId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return list;
    }

    @Override
    public String add(Long enrolleeId, Long subjectId, int subjectScore, Long facultyId) {
        int count = 0;
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_ADD);
            addLine(enrolleeId, subjectId, subjectScore, facultyId);
            count = prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return count + " row(s) added successfully.";
    }

    private void addLine(Long enrolleeId, Long subjectId, int subjectScore, Long facultyId) throws SQLException {
        prepStat.setLong(1, enrolleeId);
        prepStat.setLong(2, subjectId);
        prepStat.setInt(3, subjectScore);
        prepStat.setLong(4, facultyId);
        prepStat.addBatch();
    }

    @Override
    public List<Student> getStudentByFacultyId(Long facultyId) {
        List<Student> list = new ArrayList<>();
        try {
            conn = ConnectionService.getInstance().getConnection();
            prepStat = conn.prepareStatement(SQL_GET_RECRUITMENT_PLAN_BY_FAC_ID);
            prepStat.setLong(1, facultyId);
            resSet = prepStat.executeQuery();
            resSet.next();
            int plan = resSet.getInt("recruitment_plan");

            prepStat = conn.prepareStatement(SQL_GET_TOP_ENROLLEE);
            prepStat.setLong(1, facultyId);
            prepStat.setInt(2, plan);
            resSet = prepStat.executeQuery();

            int pos = 1;
            while (resSet.next()) {
                String firstName = resSet.getString("enrollee.first_name");
                String lastName = resSet.getString("enrollee.last_name");
                int total = resSet.getInt("total");
                list.add(new Student(pos, firstName, lastName, total));
                pos++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat, conn);
        }
        return list;
    }

}
