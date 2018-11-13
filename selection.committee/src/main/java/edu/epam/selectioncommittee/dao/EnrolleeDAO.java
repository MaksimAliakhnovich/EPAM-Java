package edu.epam.selectioncommittee.dao;

import edu.epam.selectioncommittee.entity.Enrollee;

import java.util.List;

public interface EnrolleeDAO {
    List<Enrollee> getAll();
    Enrollee getById(Long id);
    int add(String firstName, String lastName, int score, String passport);
    Long getByPassport(String passport);
}

