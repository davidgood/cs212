package org.walsh;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentParser {

    private final HashMap<AssignmentType, ArrayList<Assignment>> _assignmentMap = new HashMap<>();
    private final ArrayList<Assignment> _assignments = new ArrayList<>(); // Store assignments here

    public StudentParser() {
        // Create a mapping of AssignmentTypes to hold the assignments when they're parsed:
        for (AssignmentType type : AssignmentType.values()) {
            _assignmentMap.put(type, new ArrayList<>()); // Initialize each assignment type
        }
    }

    public ArrayList<Student> parseStudents(ArrayList<String[]> csvData) {
        ArrayList<Student> students = new ArrayList<>();
        int _ID = 0;

        // Parse header row (assignment names only)
        parseHeaderRow(csvData.getFirst());

        // Parse student data (remaining rows)
        for (int rowIndex = 1; rowIndex < csvData.size(); rowIndex++) {
            String[] row = csvData.get(rowIndex);
            var name = row[0];
            var studentType = StudentType.valueOf(row[1].toUpperCase());
            var student = creatStudent(studentType, name, ++_ID, row); //pre-increment ID so it's not Zero

            getAssignments(row, student);

            students.add(student);
        }

        return students;
    }

    private void parseHeaderRow(String[] headerRow) {
        // The first row is the header
        for (int i = 2; i < headerRow.length - 1; i += 2) {
            String assignmentName = headerRow[i]; // Only store the assignment name
            _assignments.add(new Assignment(assignmentName, 100, "2024-09-30", null)); // We’ll set type later
        }
    }

    private Student creatStudent(StudentType studentType, String name, int _ID, String[] row) {
        if (studentType == StudentType.PARTTIME) {
            int hoursWorked = Integer.parseInt(row[row.length - 1]); // Last column is hours worked
            return new PartTimeStudent(name, "ID_" + _ID, hoursWorked);
        } else if (studentType == StudentType.ONLINE) {
            var student = new OnlineStudent(name, "ID_" + _ID);
            simulateOnlineStudentData(student);
            return student;
        } else {
            return new Student(name, "ID_" + _ID, StudentType.FULLTIME);
        }
    }

    private void getAssignments(String[] row, Student student) {
        // Assign grades and assignment types to the student
        for (int i = 2; i < row.length - 1; i += 2) {
            int grade = Integer.parseInt(row[i]);
            AssignmentType type = AssignmentType.valueOf(row[i + 1].toUpperCase()); // Now parse assignment type from data row
            Assignment assignment = _assignments.get((i - 2) / 2); // Get corresponding assignment
            assignment.setType(type); // Set the type in the assignment
            student.addGrade(assignment, grade); // Assign grade to the student
            _assignmentMap.get(type).add(assignment); // Add assignment to assignmentMap
        }
    }

    private void simulateOnlineStudentData(OnlineStudent student) {
        var min = 1;
        var range = 15;
        var posts = (int)(Math.random() * range) + min;
        for (int i =0; i < posts; i++) {
            student.incrementForumPosts();
            student.completeVideoLecture();
        }
    }

}