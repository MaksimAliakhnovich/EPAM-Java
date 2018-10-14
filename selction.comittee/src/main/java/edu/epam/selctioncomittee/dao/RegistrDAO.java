package main.java.edu.epam.selctioncomittee.dao;

import main.java.edu.epam.selctioncomittee.entity.Register;

import java.util.List;

public interface RegistrDAO {
    List<Register> getAll();
    Register getById(Long id);
    Register create(Register faculty);
    List<Register> getByEnrolleeId(Long id);
}
