package main.java.edu.epam.selectioncommittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Register {
    private Long id;
    private Long enrolleeId;
    private Long subjectId;
    private Integer score;
    private Long facultyId;

    public Register(Long id, Long enrolleeId, Long subjectId, Integer score, Long facultyId) {
        this.id = id;
        this.enrolleeId = enrolleeId;
        this.subjectId = subjectId;
        this.score = score;
        this.facultyId = facultyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(Long enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return id + " " + enrolleeId + " " + subjectId + " " + score + " " + facultyId;
    }
}
