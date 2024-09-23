package org.walsh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    // Name or Path to the students.csv file
    private final String _csvFile;

    public CSVReader(String csvFile) {
        _csvFile = csvFile;
    }

    public ArrayList<String[]> readData() {
        // TODO - read each line of students.csv and split them into arrays of Strings
        // return that data
    }
}