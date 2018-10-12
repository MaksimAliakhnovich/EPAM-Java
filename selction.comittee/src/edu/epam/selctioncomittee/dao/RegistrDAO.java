package edu.epam.selctioncomittee.dao;

import edu.epam.selctioncomittee.entity.Registr;
import edu.epam.selctioncomittee.entity.Subject;

import java.util.List;

public interface RegistrDAO {
    List<Registr> getAll();
    Registr getById(Long id);
    Registr create(Registr faculty);
    List<Registr> getByEnrolleeId(Long id);
}
