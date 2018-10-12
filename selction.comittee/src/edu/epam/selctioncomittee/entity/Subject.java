package edu.epam.selctioncomittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Subject {
    private Long id;
    private String name;
    private Long facultyId;

    public Subject(Long id, String name, Long facultyId) {
        this.id = id;
        this.name = name;
        this.facultyId = facultyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
