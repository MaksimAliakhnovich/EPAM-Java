package main.java.edu.epam.selectioncommittee.dao.mysqlimpl;

import main.java.edu.epam.selectioncommittee.dao.RegisterDAO;
import main.java.edu.epam.selectioncommittee.entity.Register;
import main.java.edu.epam.selectioncommittee.service.ConnectionService;
import main.java.edu.epam.selectioncommittee.utils.CloseConnection;

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
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection cn = null;

    @Override
    public List<Register> getAll() {
        final String SQL = "SELECT id, enrollee_id, subject_id, subject_score, faculty_id FROM register;";
        List<Register> list = new ArrayList<>();

        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long enrollee_id = rs.getLong("enrollee_id");
                Long subject_id = rs.getLong("subject_id");
                int subject_score = rs.getInt("subject_score");
                Long faculty_id = rs.getLong("faculty_id");
                list.add(new Register(id, enrollee_id, subject_id, subject_score, faculty_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return list;
    }

    @Override
    public String add(Long enrollee_id,
                      Long subject_id_1, int subject_score_1,
                      Long subject_id_2, int subject_score_2,
                      Long subject_id_3, int subject_score_3,
                      Long faculty_id) {
        final String SQL = "INSERT INTO register (enrollee_id, subject_id, subject_score, faculty_id)" +
                " VALUES (?, ?, ?, ?)";
        int count = 0;
        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            ps.setLong(1, enrollee_id);
            ps.setLong(2, subject_id_1);
            ps.setInt(3, subject_score_1);
            ps.setLong(4, faculty_id);
            ps.addBatch();
            ps.setLong(1, enrollee_id);
            ps.setLong(2, subject_id_2);
            ps.setInt(3, subject_score_2);
            ps.setLong(4, faculty_id);
            ps.addBatch();
            ps.setLong(1, enrollee_id);
            ps.setLong(2, subject_id_3);
            ps.setInt(3, subject_score_3);
            ps.setLong(4, faculty_id);
            ps.addBatch();
            count = ps.executeBatch().length;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return count + " row(s) added successfully.";
    }

    @Override
    public List<Register> getByEnrolleeId(Long id) {
        return null;
    }
}
