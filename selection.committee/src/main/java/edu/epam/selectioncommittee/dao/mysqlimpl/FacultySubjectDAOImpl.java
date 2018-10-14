package main.java.edu.epam.selectioncommittee.dao.mysqlimpl;

import main.java.edu.epam.selectioncommittee.dao.FacultySubjectDAO;
import main.java.edu.epam.selectioncommittee.entity.FacultySubject;
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
public class FacultySubjectDAOImpl implements FacultySubjectDAO {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection cn = null;

    @Override
    public List<FacultySubject> getAllSubjectsByFacultyId(Long facultyId) {
        final String SQL = "SELECT subject.name FROM faculty_subject " +
                "LEFT JOIN faculty ON faculty.id = faculty_subject.faculty_id " +
                "LEFT JOIN subject ON subject.id = faculty_subject.subject_id " +
                "WHERE faculty_id = ?";
        List<FacultySubject> list = new ArrayList<>();

        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            ps.setLong(1, facultyId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sName = rs.getString("subject.name");
                list.add(new FacultySubject(sName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return list;
    }
}
