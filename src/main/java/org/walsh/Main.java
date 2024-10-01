package org.walsh;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Reading CSV data
        CSVReader csvReader = new CSVReader("students.csv");
        ArrayList<String[]> csvData = csvReader.readData();

        // Parsing student data
        StudentParser studentParser = new StudentParser();
        ArrayList<Student> students = studentParser.parseStudents(csvData);

        // Simulate API post for each student
        for (Student student : students) {
            student.simulateAPIPost();
            System.out.println("\n");
        }
    }
}