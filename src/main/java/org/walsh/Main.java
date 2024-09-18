package org.walsh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();

        // Read grades from a CSV file
        String csvFile = "students.csv";
        String line;
        String csvSplitBy = ",";

        //  TODO fix the following code in the try block to match the new students.csv format
        //  and create the appropriate objects:
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read header line to extract assignment names
            if ((line = br.readLine()) != null) {
                String[] headers = line.split(csvSplitBy);
                for (int i = 1; i < headers.length; i++) { // Skip the first column which is student name
                    assignments.add(new Assignment(headers[i], 100, "2024-09-30")); // Assign a dummy due date
                }
            }

            // Read each student's grades
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                String studentName = values[0];
                String studentId = "S" + (students.size() + 1);

                Student student = new Student(studentName, studentId);
                students.add(student);

                for (int i = 1; i < values.length; i++) { // Skip the first column which is student name
                    int grade = Integer.parseInt(values[i]);
                    student.addGrade(assignments.get(i - 1), grade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Simulate API interaction
        for (Student student : students) {
            student.simulateAPIPost();
        }
    }

}