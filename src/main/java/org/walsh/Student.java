package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private final Map<IGradeable, Grade> grades;

    public Student(String name, String id) {
        super(name, id);
        this.grades = new HashMap<>();
    }

    public void addGrade(IGradeable gradable, int grade) {
        this.grades.put(gradable, new Grade(grade));
    }

    public Integer getGrade(IGradeable gradable) {
        Grade grade = this.grades.get(gradable);
        return grade != null ? grade.getPoints() : null;
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : grades.values()) {
            total += grade.getPoints();
        }
        return total / grades.size();
    }

    @Override
    public void simulateAPIPost() {
        System.out.println("Posting student data to external system:");
        System.out.println("Student Name: " + getName());
        System.out.println("Student ID: " + getId());
        for (Map.Entry<IGradeable, Grade> entry : grades.entrySet()) {
            System.out.println("Assignment: " + entry.getKey().getName() +
                    ", Grade: " + entry.getValue().getPoints());
        }
    }
}