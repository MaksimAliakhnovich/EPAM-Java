package main.java.edu.epam.selectioncommittee.dao.connection.factories;

import main.java.edu.epam.selectioncommittee.dao.*;

/**
 * Created by mascon on 16.10.2018.
 */
public interface DAOFactory {
    EnrolleeDAO createEnrolleeDAO();
    FacultyDAO createFacultyDAO();
    FacultySubjectDAO createFacultySubjectDAO();
    RegisterDAO createRegisterDAO();
    SubjectDAO createSubjectDAO();
}
