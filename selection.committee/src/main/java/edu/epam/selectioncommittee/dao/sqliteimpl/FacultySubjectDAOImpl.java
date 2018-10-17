package main.java.edu.epam.selectioncommittee.dao.sqliteimpl;

import main.java.edu.epam.selectioncommittee.dao.FacultySubjectDAO;
import main.java.edu.epam.selectioncommittee.entity.FacultySubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public List<Long> getAllSubjectsIdByFacultyId(Long facultyId) {
        List<Long> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<FacultySubject> getAllSubjectsNameByFacultyId(Long facultyId) {
        List<FacultySubject> list = new ArrayList<>();
        return list;
    }
}
