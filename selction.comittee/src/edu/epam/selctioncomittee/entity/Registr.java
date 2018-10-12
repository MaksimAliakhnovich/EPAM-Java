package edu.epam.selctioncomittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Registr {
    private Long id;
    private Long enrolleeId;
    private Long subjectId;
    private Integer score;

    public Registr(Long id, Long enrolleeId, Long subjectId, Integer score) {
        this.id = id;
        this.enrolleeId = enrolleeId;
        this.subjectId = subjectId;
        this.score = score;
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
}
