package org.walsh;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentParser {

    private HashMap<AssignmentType, ArrayList<Assignment>> assignmentMap = new HashMap<>();
    private ArrayList<Assignment> assignments = new ArrayList<>(); // Store assignments here

    public StudentParser() {
        // This code parses each assignment type that's in the AssignmentType enum and
        // puts it into a hashmap as the key and creates a new ArrayList<> as the value
        // to hold the actual assignments when you're parsing the csvData.
        for (AssignmentType type : AssignmentType.values()) {
            assignmentMap.put(type, new ArrayList<>()); // Initialize each assignment type
        }
    }

    public ArrayList<Student> parseStudents(ArrayList<String[]> csvData) {
        ArrayList<Student> students = new ArrayList<>();

        // TODO - parse the data you get from CSVReader.
        // i.e. every element of csvData should be one line of the .csv file and
        // be an array of Strings that represent each column of the .csv file.

        return students;
    }

}