package main.java.edu.epam.selectioncommittee.dao;

import main.java.edu.epam.selectioncommittee.entity.FacultySubject;

import java.util.List;

/**
 * Created by mascon on 14.10.2018.
 */
public interface FacultySubjectDAO {
    List<FacultySubject> getAllSubjectsNameByFacultyId(Long facultyId);
    List<Long> getAllSubjectsIdByFacultyId(Long facultyId);

}
