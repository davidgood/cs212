package org.walsh;

import java.util.ArrayList;

public class Student {
    private final String name;
    private final String id;
    private final ArrayList<Grade> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addGrade(Assignment assignment, int grade) {
        // TODO
    }

    public Integer getGrade(Assignment assignment) {
        // TODO
    }

    public double calculateAverageGrade() {
        // TODO
    }

    public void simulateAPIPost() {
        // TODO
    }
}