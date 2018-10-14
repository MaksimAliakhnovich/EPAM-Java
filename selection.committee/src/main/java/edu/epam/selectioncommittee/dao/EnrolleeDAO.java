package main.java.edu.epam.selectioncommittee.dao;

import main.java.edu.epam.selectioncommittee.entity.Enrollee;

import java.util.List;

public interface EnrolleeDAO {
    List<Enrollee> getAll();
    Enrollee getById(Long id);
    String add(String first_name, String last_name, int score);
    Long getByFNameAndLNameAndScore(String first_name, String last_name, int score); // нужно по паспорту искать
}

