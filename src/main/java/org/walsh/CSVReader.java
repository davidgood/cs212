package org.walsh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    private String csvFile;

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public ArrayList<String[]> readData() {
        ArrayList<String[]> data = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}