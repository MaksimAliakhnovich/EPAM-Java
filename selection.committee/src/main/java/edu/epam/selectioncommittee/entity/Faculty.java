package edu.epam.selectioncommittee.entity;

/**
 * Created by mascon on 11.10.2018.
 */
public class Faculty {
    private Long id;
    private String name;
    private Integer plan;

    public Faculty(Long id, String name, Integer plan) {
        this.id = id;
        this.name = name;
        this.plan = plan;
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

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Факультет №" + id + ": " + name + ", план набора " + plan;
    }
}
