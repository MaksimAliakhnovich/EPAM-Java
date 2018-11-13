package edu.epam.selectioncommittee.utils;

/**
 * Created by mascon on 14.10.2018.
 */
public class Student {
    private int position;
    private String firstName;
    private String lastName;
    private int totalScore;

    public Student(int position, String firstName, String lastName, int totalScore) {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return position + ": " + firstName + " " + lastName + ", баллы: " + totalScore;
    }
}
