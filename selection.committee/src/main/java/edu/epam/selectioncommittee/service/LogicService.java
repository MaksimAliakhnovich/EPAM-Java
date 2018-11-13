package edu.epam.selectioncommittee.service;

import edu.epam.selectioncommittee.dao.EnrolleeDAO;
import edu.epam.selectioncommittee.dao.FacultyDAO;
import edu.epam.selectioncommittee.dao.FacultySubjectDAO;
import edu.epam.selectioncommittee.dao.RegisterDAO;
import edu.epam.selectioncommittee.dao.factories.DAOFactory;
import edu.epam.selectioncommittee.entity.Faculty;
import edu.epam.selectioncommittee.entity.FacultySubject;
import edu.epam.selectioncommittee.utils.LocaleManager;
import edu.epam.selectioncommittee.utils.Student;

import java.util.List;

/**
 * Created by mascon on 13.10.2018.
 */
public class LogicService {
    private EnrolleeDAO enrolleeDAO;
    private FacultyDAO facultyDAO;
    private FacultySubjectDAO facultySubjectDAO;
    private RegisterDAO registerDAO;

    private Long enrolleeId = 0L;
    private int subScore1 = 0;
    private int subScore2 = 0;
    private int subScore3 = 0;
    private Long facultyId = 0L;

    private LocaleManager manager = LocaleManager.INSTANCE.getInstance();

    public LogicService(DAOFactory daoFactory) {
        enrolleeDAO = daoFactory.createEnrolleeDAO();
        facultyDAO = daoFactory.createFacultyDAO();
        facultySubjectDAO = daoFactory.createFacultySubjectDAO();
        registerDAO = daoFactory.createRegisterDAO();
    }

    // получение всех факультетов
    public List<Faculty> getAllFac() {
        return facultyDAO.getAll();
    }

    // получение предметов по выбранному факультету
    public List<FacultySubject> getAllSubNameByFacId(Long id) {
        return facultySubjectDAO.getAllSubjectsNameByFacultyId(id);
    }

    // добавление абитуриента с аттестатом
    public void addEnrollee(String firstName, String lastName, int certificateScore, String passport) {
        int add = enrolleeDAO.add(firstName, lastName, certificateScore, passport);
        if (add == 1) {
            enrolleeId = enrolleeDAO.getByPassport(passport);
            System.out.println(manager.getString("yourDataIsListed"));
        } else {
            System.out.println(manager.getString("yourDataIsNotListed"));
        }
    }

    // сбор баллов по предметам факультета
    public void getSubScore(Long facultyId, int subScore1, int subScore2, int subScore3) {
        this.facultyId = facultyId;
        this.subScore1 = subScore1;
        this.subScore2 = subScore2;
        this.subScore3 = subScore3;
    }

    // добавление студента с баллами по 3м предметам в регистр
    public void addRegLine() {
        int add = 0;
        List<Long> subsId = facultySubjectDAO.getAllSubjectsIdByFacultyId(facultyId);
        add += registerDAO.add(enrolleeId, subsId.get(0), subScore1, facultyId);
        add += registerDAO.add(enrolleeId, subsId.get(1), subScore2, facultyId);
        add += registerDAO.add(enrolleeId, subsId.get(2), subScore3, facultyId);
        if (add == 3) {
            enrolleeId = 0L;
            subScore1 = 0;
            subScore2 = 0;
            subScore3 = 0;
            facultyId = 0L;
            System.out.println(manager.getString("yourDataIsListed"));
        } else {
            System.out.println(manager.getString("yourDataIsNotListed"));
        }
    }

    // подсчёт зачисленных на факультеты
    public List<Student> getStudentByFacId(Long id) {
        return registerDAO.getStudentByFacultyId(id);
    }

    // построчный вывод коллекции в консоль
    public <T> void print(List<T> lines) {
        for (T line : lines) {
            System.out.println(line.toString());
        }
    }
}