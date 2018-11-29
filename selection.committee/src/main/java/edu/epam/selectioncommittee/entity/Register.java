package edu.epam.selectioncommittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Register {
    private Long id;
    private String enrolleePassport;
    private Long subjectId;
    private Integer score;
    private Long facultyId;

    public Register() {
    }

    public Register(Long id, String enrolleePassport, Long subjectId, Integer score, Long facultyId) {
        this.id = id;
        this.enrolleePassport = enrolleePassport;
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

    public String getEnrolleePassport() {
        return enrolleePassport;
    }

    public void setEnrolleePassport(String enrolleePassport) {
        this.enrolleePassport = enrolleePassport;
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
        return id + " " + enrolleePassport + " " + subjectId + " " + score + " " + facultyId;
    }
}
