package org.walsh.hw2;

public class Grade {
    private final Assignment assignment;
    private final int grade;

    public Grade(Assignment assignment, int grade) {
        this.assignment = assignment;
        this.grade = grade;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public int getGrade() {
        return grade;
    }
}
