package edu.epam.selectioncommittee.service;

import edu.epam.selectioncommittee.dao.FacultyDAO;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.entity.Faculty;

import java.util.List;

/**
 * Created by mascon on 17.11.2018.
 */
public class FacultyService {
    private FacultyDAO facultyDAO;

    public FacultyService(DAOFactory daoFactory) {
        facultyDAO = daoFactory.createFacultyDAO();
    }

    // получение всех факультетов
    public List<Faculty> getAllFac() {
        return facultyDAO.getAll();
    }
}
