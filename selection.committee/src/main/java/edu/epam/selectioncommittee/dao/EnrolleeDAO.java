package edu.epam.selectioncommittee.dao;

import edu.epam.selectioncommittee.entity.Enrollee;

import java.util.List;

public interface EnrolleeDAO {
    List<Enrollee> getAll();
    Enrollee getById(Long id);
    int add(String firstName, String lastName, int score, String passport);
    int delete(String passport);
    int update(String firstName, String lastName, int score, String passport, String oldPassport);
    Enrollee getByPassport(String passport);
}

