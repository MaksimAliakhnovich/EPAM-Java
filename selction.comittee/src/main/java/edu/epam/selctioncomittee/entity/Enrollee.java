package main.java.edu.epam.selctioncomittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Enrollee {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer certificateScore;

    public Enrollee(Long id, String firstName, String lastName, Integer certificateScore) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.certificateScore = certificateScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCertificateScore() {
        return certificateScore;
    }

    public void setCertificateScore(Integer certificateScore) {
        this.certificateScore = certificateScore;
    }
}
