package edu.epam.selectioncommittee.dao.sqliteimpl;

import edu.epam.selectioncommittee.dao.EnrolleeDAO;
import edu.epam.selectioncommittee.entity.Enrollee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public class EnrolleeDAOImpl implements EnrolleeDAO {
    private final static String SQL_GET_ALL = "SELECT id, first_name, last_name, certificate_score FROM enrollee;";
    private final static String SQL_GET_BY_ID = "SELECT id, first_name, last_name, certificate_score FROM enrollee WHERE id = ?;";
    private final static String SQL_ADD = "INSERT INTO enrollee (first_name, last_name, certificate_score, passport) VALUES (?, ?, ?, ?);";
    private final static String SQL_GET_BY_PASSPORT = "SELECT id FROM enrollee WHERE passport = ?;";
    private PreparedStatement prepStat = null;
    private ResultSet resSet = null;
    private Connection conn = null;


    @Override
    public List<Enrollee> getAll() {
        List<Enrollee> list = new ArrayList<>();
        return list;
    }

    @Override
    public Enrollee getById(Long reqId) {
        Enrollee enrollee = null;
        return enrollee;
    }

    @Override
    public int add(String firstName, String lastName, int score, String passport) {
        int count = 0;
        return count;
    }

    @Override
    public Long getByPassport(String passport) {
        Long id = 0L;
        return id;
    }
}
