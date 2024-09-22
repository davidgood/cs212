package org.walsh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static int _ID = 0;


    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();

        // Read grades from a CSV file
        String csvFile = "students.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read header line to extract assignment names
            if ((line = br.readLine()) != null) {
                String[] headers = line.split(csvSplitBy);
                for (int i = 2; i < headers.length; i++) { // Start at index 2 to skip Name and Type columns
                    assignments.add(new Assignment(headers[i], 100, "2024-09-30")); // Assign a dummy due date
                }
            }

            // Read each student's grades
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy);
                Student student = getStudent(row);

                // Add the student's grades
                for (int i = 2; i < row.length; i++) {
                    student.addGrade(assignments.get(i - 2), Integer.parseInt(row[i])); // Grades start at index 2
                }

                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Simulate posting to an external API
        for (Student student : students) {
            student.simulateAPIPost();
        }
    }

    private static Student getStudent(String[] row) {;
        String studentName = row[0];
        String studentType = row[1];

        // Create student based on type
        Student student;
        if (studentType.equalsIgnoreCase("Online")) {
            OnlineStudent onlineStudent = new OnlineStudent(studentName, "S" + ++_ID);
            onlineStudent.incrementForumPosts(); // Simulate some forum posts for this online student
            onlineStudent.completeVideoLecture(); // Simulate some video lectures completed
            student = onlineStudent;
        } else {
            student = new Student(studentName, "S" + ++_ID);
        }
        return student;
    }
}