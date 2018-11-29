package edu.epam.selectioncommittee.dao.mysqlimpl;

import edu.epam.selectioncommittee.dao.RegisterDAO;
import edu.epam.selectioncommittee.entity.Register;
import edu.epam.selectioncommittee.utils.ConfigurationManager;
import edu.epam.selectioncommittee.utils.Student;
import edu.epam.selectioncommittee.utils.DBConnectionPool;
import edu.epam.selectioncommittee.utils.CloseConnection;

import javax.sql.rowset.spi.SyncResolver;
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
    private final static String SQL_GET_ALL =
            ConfigurationManager.INSTANCE.getInstance().getProperty("registerSqlGetAll");
    private final static String SQL_ADD =
            ConfigurationManager.INSTANCE.getInstance().getProperty("registerAdd");
    private final static String SQL_GET_RECRUITMENT_PLAN_BY_FAC_ID =
            ConfigurationManager.INSTANCE.getInstance().getProperty("registerGetRecruitmentPlanByFacId");
    private final static String SQL_GET_TOP_ENROLLEE =
            ConfigurationManager.INSTANCE.getInstance().getProperty("registerGetTopEnrollee");
    private final static String SQL_DELETE_LINE_BY_PASSPORT =
            ConfigurationManager.INSTANCE.getInstance().getProperty("registerDeleteLineByPassport");

    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;
    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    @Override
    public List<Register> getAll() {
        List<Register> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_GET_ALL);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                String enrolleePassport = resSet.getString("enrollee_passport");
                Long subjectId = resSet.getLong("subject_id");
                int subjectScore = resSet.getInt("subject_score");
                Long facultyId = resSet.getLong("faculty_id");
                list.add(new Register(id, enrolleePassport, subjectId, subjectScore, facultyId));
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
    public int add(String enrolleePassport, Long subjectId, int subjectScore, Long facultyId) {
        int count = 0;
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_ADD);
            addLine(enrolleePassport, subjectId, subjectScore, facultyId);
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
    public int delete(String enrolleePassport) {
        int count = 0;
        try {
            conn = dbConnectionPool.getPoolConnection();
            prepStat = conn.prepareStatement(SQL_DELETE_LINE_BY_PASSPORT);
            prepStat.setString(1, enrolleePassport);
            count = prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return count;
    }

    private void addLine(String enrolleePassport, Long subjectId, int subjectScore, Long facultyId) throws SQLException {
        prepStat.setString(1, enrolleePassport);
        prepStat.setLong(2, subjectId);
        prepStat.setInt(3, subjectScore);
        prepStat.setLong(4, facultyId);
        prepStat.addBatch();
    }

    @Override
    public List<Student> getStudentByFacultyId(Long facultyId) {
        List<Student> list = new ArrayList<>();
        try {
            conn = dbConnectionPool.getPoolConnection();
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
            CloseConnection.closeConnection(resSet, prepStat);
            dbConnectionPool.putPoolConnection(conn);
        }
        return list;
    }
}
