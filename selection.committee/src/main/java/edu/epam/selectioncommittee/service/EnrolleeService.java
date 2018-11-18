package edu.epam.selectioncommittee.service;

import edu.epam.selectioncommittee.dao.EnrolleeDAO;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.entity.Enrollee;

import java.util.List;

/**
 * Created by mascon on 17.11.2018.
 */
public class EnrolleeService {
    private EnrolleeDAO enrolleeDAO;

    public EnrolleeService(DAOFactory daoFactory) {
        enrolleeDAO = daoFactory.createEnrolleeDAO();
    }

    // добавление абитуриента с аттестатом
    public void addEnrollee(String firstName, String lastName, int certificateScore, String passport) {
        enrolleeDAO.add(firstName, lastName, certificateScore, passport);
//        if (add == 1) {
//            enrolleeId = enrolleeDAO.getByPassport(passport);
//            System.out.println(manager.getString("yourDataIsListed")); //todo убрать текст из проперти, если не пригодится
//        } else {
//            System.out.println(manager.getString("yourDataIsNotListed"));
//        }
    }

    // получение всех студентов
    public List<Enrollee> getAllEnrollee() {
        return enrolleeDAO.getAll();
    }

    public Enrollee getEnrolleeByPassport(String passport) {
        return enrolleeDAO.getByPassport(passport);
    }

    public void deleteEnrolleeByPassport(String passport) {
        enrolleeDAO.delete(passport);
    }

    public void updateEnrollee(String firstName, String lastName, int certificateScore, String passport, String oldPassport) {
        enrolleeDAO.update(firstName, lastName, certificateScore, passport, oldPassport);
    }
}
