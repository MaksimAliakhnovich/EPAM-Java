package main.java.edu.epam.selectioncommittee.service;

import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.EnrolleeDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.FacultyDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.FacultySubjectDAOImpl;
import main.java.edu.epam.selectioncommittee.dao.mysqlimpl.RegisterDAOImpl;
import main.java.edu.epam.selectioncommittee.entity.Faculty;
import main.java.edu.epam.selectioncommittee.entity.FacultySubject;
import main.java.edu.epam.selectioncommittee.utils.Student;

import java.util.List;

/**
 * Created by mascon on 13.10.2018.
 */
public class LogicService {

    private Long enrolleeId = 0L;
    private int subScore1 = 0;
    private int subScore2 = 0;
    private int subScore3 = 0;
    private Long facultyId = 0L;

    // получение всех факультетов
    public List<Faculty> getAllFac() {
        FacultyDAOImpl facDAO = new FacultyDAOImpl();
        return facDAO.getAll();
    }

    // получение предметов по выбранному факультету
    public List<FacultySubject> getAllSubNameCurFac(Long id) {
        FacultySubjectDAOImpl facSubDAO = new FacultySubjectDAOImpl();
        return facSubDAO.getAllSubjectsNameByFacultyId(id);
    }

    // добавление абитуриента с аттестатом
    public void addEnrollee(String firstName, String lastName, int certificateScore, String passport) {
        EnrolleeDAOImpl enrolleeDAO = new EnrolleeDAOImpl();
        System.out.println(enrolleeDAO.add(firstName, lastName, certificateScore, passport));
        enrolleeId = enrolleeDAO.getByPassport(passport);
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
        List<Long> subsId = new FacultySubjectDAOImpl().getAllSubjectsIdByFacultyId(facultyId);
        /*сделать 3 раза добавление строки*/
        System.out.println(regDAO.add(
                enrolleeId,
                subsId.get(0), subScore1,
                subsId.get(1), subScore2,
                subsId.get(2), subScore3,
                facultyId));
        enrolleeId = 0L;
        subScore1 = 0;
        subScore2 = 0;
        subScore3 = 0;
        facultyId = 0L;
    }

    // подсчёт зачисленных на факультеты
    public List<Student> getStudentByFacId(Long id) {
        RegisterDAOImpl regDAO = new RegisterDAOImpl();
        return regDAO.getStudentByFacultyId(id);
    }

    // построчный вывод коллекции в консоль
    public <T> void print(List<T> lines) {
        for (T line : lines) {
            System.out.println(line.toString());
        }
    }
}