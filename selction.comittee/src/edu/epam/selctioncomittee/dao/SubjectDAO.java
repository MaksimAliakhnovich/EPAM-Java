package edu.epam.selctioncomittee.dao;

import edu.epam.selctioncomittee.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getAll();
    Subject getById(Long id);
    Subject create(Subject faculty);
}
