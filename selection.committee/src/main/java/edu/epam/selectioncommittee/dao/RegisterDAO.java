package edu.epam.selectioncommittee.dao;

import edu.epam.selectioncommittee.entity.Register;
import edu.epam.selectioncommittee.utils.Student;

import java.util.List;

public interface RegisterDAO {
    List<Register> getAll();
    int add(Long enrolleeId, Long subjectId, int subjectScore, Long facultyId);
    List<Student> getStudentByFacultyId(Long facultyId);
}
