package main.java.edu.epam.selectioncommittee.dao.factories;

import main.java.edu.epam.selectioncommittee.dao.*;
import main.java.edu.epam.selectioncommittee.dao.sqliteimpl.EnrolleeDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.sqliteimpl.FacultyDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.sqliteimpl.FacultySubjectDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.sqliteimpl.RegisterDAOImpl;

/**
 * Created by mascon on 16.10.2018.
 */
public class SqliteDAOFactory implements DAOFactory{
    @Override
    public EnrolleeDAO createEnrolleeDAO() {
        return new EnrolleeDAOImpl();
    }

    @Override
    public FacultyDAO createFacultyDAO() {
        return new FacultyDAOImpl();
    }

    @Override
    public FacultySubjectDAO createFacultySubjectDAO() {
        return new FacultySubjectDAOImpl();
    }

    @Override
    public RegisterDAO createRegisterDAO() {
        return new RegisterDAOImpl();
    }

    @Override
    public SubjectDAO createSubjectDAO() {
        return null;
    }
}
