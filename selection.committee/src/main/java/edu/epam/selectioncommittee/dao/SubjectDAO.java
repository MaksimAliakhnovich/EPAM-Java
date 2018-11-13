package edu.epam.selectioncommittee.dao;

import edu.epam.selectioncommittee.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getAll();
    Subject getById(Long id); // на будущее
    Subject create(Subject faculty); // на будущее
}
