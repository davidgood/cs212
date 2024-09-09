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
        this.grades.add(new Grade(assignment, grade));
    }

    public Integer getGrade(Assignment assignment) {
        for (Grade grade : grades) {
            if (grade.getAssignment().equals(assignment)) {
                return grade.getGrade();
            }
        }
        return null;
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : grades) {
            total += grade.getGrade();
        }
        return total / grades.size();
    }

    public void simulateAPIPost() {
        System.out.println("Posting student data to external system:");
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        for (Grade grade : grades) {
            System.out.println("Assignment: " + grade.getAssignment().getName() +
                    ", Grade: " + grade.getGrade());
        }
    }
}