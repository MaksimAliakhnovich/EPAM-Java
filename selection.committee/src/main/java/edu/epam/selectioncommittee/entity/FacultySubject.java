package main.java.edu.epam.selectioncommittee.entity;

/**
 * Created by mascon on 14.10.2018.
 */
public class FacultySubject {
    private int subjectNumber;
    private String subjectName;

    public FacultySubject(int subjectNumber, String subjectName) {
        this.subjectNumber = subjectNumber;
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(int subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    @Override
    public String toString() {
        return "Предмет " + subjectNumber + ": " + subjectName;
    }
}
