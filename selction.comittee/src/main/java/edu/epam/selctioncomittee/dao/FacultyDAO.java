package main.java.edu.epam.selctioncomittee.dao;

import main.java.edu.epam.selctioncomittee.entity.Faculty;

import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public interface FacultyDAO {
    List<Faculty> getAll();
    Faculty getById(Long id);
    Faculty create(Faculty faculty);
}



