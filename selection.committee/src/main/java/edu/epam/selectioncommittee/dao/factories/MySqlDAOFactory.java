package edu.epam.selectioncommittee.dao.factories;

import edu.epam.selectioncommittee.dao.*;
import edu.epam.selectioncommittee.dao.mysqlimpl.EnrolleeDAOImpl;
import edu.epam.selectioncommittee.dao.mysqlimpl.FacultyDAOImpl;
import edu.epam.selectioncommittee.dao.mysqlimpl.FacultySubjectDAOImpl;
import edu.epam.selectioncommittee.dao.mysqlimpl.RegisterDAOImpl;

/**
 * Created by mascon on 16.10.2018.
 */
public class MySqlDAOFactory implements DAOFactory {
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
