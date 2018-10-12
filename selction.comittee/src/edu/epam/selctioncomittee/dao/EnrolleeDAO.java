package edu.epam.selctioncomittee.dao;

import edu.epam.selctioncomittee.entity.Enrollee;
import edu.epam.selctioncomittee.entity.Registr;

import java.util.List;

public interface EnrolleeDAO {
    List<Enrollee> getAll();
    Enrollee getById(Long id);
    Enrollee create(Enrollee faculty);
}

