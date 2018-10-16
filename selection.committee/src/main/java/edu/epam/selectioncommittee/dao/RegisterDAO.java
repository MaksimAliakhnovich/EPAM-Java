package main.java.edu.epam.selectioncommittee.dao;

import main.java.edu.epam.selectioncommittee.entity.Register;
import main.java.edu.epam.selectioncommittee.utils.Student;

import java.util.List;

public interface RegisterDAO {
    List<Register> getAll();
    String add(Long enrollee_id,
               Long subject_id_1, int subject_score_1,
               Long subject_id_2, int subject_score_2,
               Long subject_id_3, int subject_score_3,
               Long faculty_id);
    List<Student> getStudentByFacultyId(Long faculty_id);
}
