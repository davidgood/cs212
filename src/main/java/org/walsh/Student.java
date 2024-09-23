package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private final Map<IGradable, Grade> _grades;

    public Student(String name, String id) {
        super(name, id);
        _grades = new HashMap<>();
        // TODO - need to get a studentType in the constructor and save it in a field.
    }

    public void addGrade(IGradable gradable, int grade) {
        _grades.put(gradable, new Grade(grade));
    }

    public Integer getGrade(IGradable gradable) {
        var grade = _grades.get(gradable);
        return grade != null ? grade.getPoints() : null;
    }

    public double calculateAverageGrade() {
        if (_grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : _grades.values()) {
            total += grade.getPoints();
        }
        return total / _grades.size();
    }

    @Override
    public void simulateAPIPost() {
        System.out.println("Posting student data to external system:");
        System.out.println("Student Name: " + getName());
        System.out.println("Student ID: " + getId());
        // TODO - print the student type here.
        for (Map.Entry<IGradable, Grade> entry : _grades.entrySet()) {
            System.out.println("Assignment: " + entry.getKey().getName() +
                    ", Grade: " + entry.getValue().getPoints());
        }
    }
}