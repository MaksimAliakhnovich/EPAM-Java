package main.java.edu.epam.selectioncommittee.dao;

import main.java.edu.epam.selectioncommittee.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getAll();
    Subject getById(Long id);
    Subject create(Subject faculty);
}
