package main.java.edu.epam.selectioncommittee.service;

import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.EnrolleeDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.FacultyDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.FacultySubjectDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.RegisterDAOImpl;
import main.java.edu.epam.selectioncommittee.entity.Faculty;
import main.java.edu.epam.selectioncommittee.entity.FacultySubject;

import java.util.List;

/**
 * Created by mascon on 13.10.2018.
 */
public class LogicService {

    private Long facultyId = 0L;
    private int subScore1 = 0;
    private int subScore2 = 0;
    private int subScore3 = 0;
    private Long enrolleeId = 0L;


    // получение всех факультетов
    public List<Faculty> getAllFac() {
        FacultyDAOImpl facDAO = new FacultyDAOImpl();
        return facDAO.getAll();
    }

    // получение предметов по выбранному факультету
    public List<FacultySubject> getAllSubCurFac(Long id) {
        FacultySubjectDAOImpl facSubDAO = new FacultySubjectDAOImpl();
        return facSubDAO.getAllSubjectsByFacultyId(id);
    }

    // добавление абитуриента с аттестатом
    public void addEnrollee(String firstName, String lastName, int certificateScore){
        EnrolleeDAOImpl enrolleeDAO = new EnrolleeDAOImpl();
        System.out.println(enrolleeDAO.add(firstName, lastName, certificateScore));
        enrolleeId = enrolleeDAO.getByFNameAndLNameAndScore(firstName, lastName, certificateScore);
    }

    // сбор баллов по предметам факультета
    public void getSubScore(Long facultyId, int subScore1, int subScore2, int subScore3) {
        this.facultyId = facultyId;
        this.subScore1 = subScore1;
        this.subScore2 = subScore2;
        this.subScore3 = subScore3;
    }

    // добавление записи в регистр
    public void addRegLine() {
        RegisterDAOImpl regDAO = new RegisterDAOImpl();
        FacultySubjectDAOImpl facSubDAO = new FacultySubjectDAOImpl();
        System.out.println(regDAO.add(
                enrolleeId,
                1L, subScore1,
                2L, subScore2,
                3L, subScore3,
                facultyId));
    }

    // построчный вывод коллекции в консоль
    public <T> void print(List<T> lines) {
        for (T line : lines) {
            System.out.println(line.toString());
        }
    }
}

    /*List<Register> registers = registerDAO.getAll();
        for (Register register : registers) {
                System.out.println(register.toString());*/

    /*        EnrolleeDAOImpl enrolleeDAO = new EnrolleeDAOImpl();
        List<Enrollee> list = enrolleeDAO.getAll();
        for (Enrollee enrollee : list) {
            System.out.println(enrollee.toString());
        }
        System.out.println(enrolleeDAO.getById(3L).toString());
*/