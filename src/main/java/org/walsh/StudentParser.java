package org.walsh;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentParser {

    private HashMap<AssignmentType, ArrayList<Assignment>> assignmentMap = new HashMap<>();
    private ArrayList<Assignment> assignments = new ArrayList<>(); // Store assignments here

    public StudentParser() {
        for (AssignmentType type : AssignmentType.values()) {
            assignmentMap.put(type, new ArrayList<>()); // Initialize each assignment type
        }
    }

    public ArrayList<Student> parseStudents(ArrayList<String[]> csvData) {
        ArrayList<Student> students = new ArrayList<>();
        int _ID = 0;

        // Parse header row (assignment names only)
        String[] headerRow = csvData.get(0); // The first row is the header
        for (int i = 2; i < headerRow.length - 1; i += 2) {
            String assignmentName = headerRow[i]; // Only store the assignment name
            assignments.add(new Assignment(assignmentName, 100, "2024-09-30", null)); // We’ll set type later
        }

        // Parse student data (remaining rows)
        for (int rowIndex = 1; rowIndex < csvData.size(); rowIndex++) {
            String[] row = csvData.get(rowIndex);
            String name = row[0];
            StudentType studentType = StudentType.valueOf(row[1].toUpperCase());
            Student student;

            if (studentType == StudentType.PARTTIME) {
                int hoursWorked = Integer.parseInt(row[row.length - 1]); // Last column is hours worked
                student = new PartTimeStudent(name, "ID_" + _ID++, hoursWorked);
            } else if (studentType == StudentType.ONLINE) {
                student = new OnlineStudent(name, "ID_" + _ID++);
            } else {
                student = new Student(name, "ID_" + _ID++, StudentType.FULLTIME);
            }

            // Assign grades and assignment types to the student
            for (int i = 2; i < row.length - 1; i += 2) {
                int grade = Integer.parseInt(row[i]);
                AssignmentType type = AssignmentType.valueOf(row[i + 1].toUpperCase()); // Now parse assignment type from data row
                Assignment assignment = assignments.get((i - 2) / 2); // Get corresponding assignment
                assignment.setType(type); // Set the type in the assignment
                student.addGrade(assignment, grade); // Assign grade to the student
                assignmentMap.get(type).add(assignment); // Add assignment to assignmentMap
            }

            students.add(student);
        }

        return students;
    }

    public HashMap<AssignmentType, ArrayList<Assignment>> getAssignmentMap() {
        return assignmentMap;
    }
}