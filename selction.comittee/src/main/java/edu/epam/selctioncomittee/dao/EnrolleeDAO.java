package main.java.edu.epam.selctioncomittee.dao;

import main.java.edu.epam.selctioncomittee.entity.Enrollee;

import java.util.List;

public interface EnrolleeDAO {
    List<Enrollee> getAll();
    Enrollee getById(Long id);
    Enrollee create(Enrollee faculty);
}

