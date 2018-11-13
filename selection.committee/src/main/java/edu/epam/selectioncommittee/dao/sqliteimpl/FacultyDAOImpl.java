package edu.epam.selectioncommittee.dao.sqliteimpl;

import edu.epam.selectioncommittee.dao.FacultyDAO;
import edu.epam.selectioncommittee.entity.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 14.10.2018.
 */
public class FacultyDAOImpl implements FacultyDAO {
    private final static String SQL_GET_ALL = "SELECT id, name, recruitment_plan FROM faculty;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;

    @Override
    public List<Faculty> getAll() {
        List<Faculty> list = new ArrayList<>();
        return list;
    }
}
