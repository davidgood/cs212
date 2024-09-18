package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private final Map<IGradable, Grade> _grades;

    public Student(String name, String id) {
        super(name, id);
        _grades = new HashMap<>();
    }

    // --- Don't change anything above this line ---
    // v--- TODO - fix the rest of this class ---v


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addGrade(Assignment assignment, int grade) {
        _grades.add(new Grade(assignment, grade));
    }

    public Integer getGrade(Assignment assignment) {
        for (Grade grade : _grades) {
            if (grade.getAssignment().equals(assignment)) {
                return grade.getGrade();
            }
        }
        return null;
    }

    public double calculateAverageGrade() {
        if (_grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : _grades) {
            total += grade.getGrade();
        }
        return total / _grades.size();
    }

    public void simulateAPIPost() {
        System.out.println("Posting student data to external system:");
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        for (Grade grade : _grades) {
            System.out.println("Assignment: " + grade.getAssignment().getName() +
                    ", Grade: " + grade.getGrade());
        }
    }
}