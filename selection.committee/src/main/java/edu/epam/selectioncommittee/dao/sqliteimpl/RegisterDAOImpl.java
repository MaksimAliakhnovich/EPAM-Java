package edu.epam.selectioncommittee.dao.sqliteimpl;

import edu.epam.selectioncommittee.dao.RegisterDAO;
import edu.epam.selectioncommittee.entity.Register;
import edu.epam.selectioncommittee.utils.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 13.10.2018.
 */
public class RegisterDAOImpl implements RegisterDAO {
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
        return list;
    }

    @Override
    public int add(String enrolleePassport, Long subjectId, int subjectScore, Long facultyId) {
        int count = 0;
        return count;
    }

    @Override
    public int delete(String enrolleePassport) {
        return 0;
    }

    private void addLine(Long enrolleeId, Long subjectId, int subjectScore, Long facultyId) throws SQLException {
    }

    @Override
    public List<Student> getStudentByFacultyId(Long facultyId) {
        List<Student> list = new ArrayList<>();
        return list;
    }

}
