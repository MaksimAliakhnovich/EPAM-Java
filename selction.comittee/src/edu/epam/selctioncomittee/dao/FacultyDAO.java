package edu.epam.selctioncomittee.dao;

import edu.epam.selctioncomittee.entity.Faculty;
import edu.epam.selctioncomittee.entity.Subject;

import java.util.List;

/**
 * Created by mascon on 11.10.2018.
 */
public interface FacultyDAO {
    List<Faculty> getAll();
    Faculty getById(Long id);
    Faculty create(Faculty faculty);
}



