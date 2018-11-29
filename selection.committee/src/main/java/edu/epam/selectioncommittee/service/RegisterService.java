package edu.epam.selectioncommittee.service;

import edu.epam.selectioncommittee.dao.RegisterDAO;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;

/**
 * Created by mascon on 28.11.2018.
 */
public class RegisterService {
    private RegisterDAO registerDAO;

    public RegisterService(DAOFactory daoFactory) {
        registerDAO = daoFactory.createRegisterDAO();
    }

    public void addLine(String passport, Long subjectId, int subjectScore, Long facultyId) {
        registerDAO.add(passport, subjectId, subjectScore, facultyId);
    }

    public void deleteFromRegister(String enrolleePassport) {
        registerDAO.delete(enrolleePassport);
    }
}
