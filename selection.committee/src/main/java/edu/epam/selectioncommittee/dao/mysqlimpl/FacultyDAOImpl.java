package main.java.edu.epam.selectioncommittee.dao.mysqlimpl;

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
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection cn = null;

    @Override
    public List<Faculty> getAll() {
        final String SQL = "SELECT id, name, recruitment_plan FROM faculty;";
        List<Faculty> list = new ArrayList<>();

        try {
            cn = ConnectionService.getInstance().getConnection();
            ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int recruitment_plan = rs.getInt("recruitment_plan");
                list.add(new Faculty(id, name, recruitment_plan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.closeConnection(rs, ps, cn);
        }
        return list;
    }

    @Override
    public Faculty getById(Long id) {
        return null;
    }

    @Override
    public Faculty create(Faculty faculty) {
        return null;
    }
}
