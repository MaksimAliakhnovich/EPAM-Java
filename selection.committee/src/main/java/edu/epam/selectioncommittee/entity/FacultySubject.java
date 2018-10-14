package main.java.edu.epam.selectioncommittee.entity;

/**
 * Created by mascon on 14.10.2018.
 */
public class FacultySubject {
    private String subjectName;

    public FacultySubject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return subjectName;
    }
}
