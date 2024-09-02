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

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read header line to extract assignment names
            // TODO

            // Read each student's grades
            while ((line = br.readLine()) != null) {
                // TODO
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate and display the average grade for each assignment
        // TODO

        // Simulate API interaction
        // TODO

        // Sort students by average grade and display the sorted list
        // TODO

    }

    public static double calculateAverageGrade(ArrayList<Student> students, Assignment assignment) {
        // TODO
    }
}