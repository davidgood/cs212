package org.walsh;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class StudentParserTest {

    private CSVReader mockCSVReader;

    @BeforeEach
    public void setUp() {

        mockCSVReader = Mockito.mock(CSVReader.class);

        // Simulate the contents of the students.csv file
        ArrayList<String[]> mockCSVData = new ArrayList<>();
        mockCSVData.add(new String[] {"Name", "Type", "Assignment1", "AssignmentType1", "Assignment2", "AssignmentType2", "HoursWorkedPerWeek"});
        mockCSVData.add(new String[] {"Owen Moore", "FullTime", "99", "EXAM", "84", "QUIZ"});
        mockCSVData.add(new String[] {"David King", "PartTime", "73", "PROJECT", "93", "QUIZ", "11"});
        mockCSVData.add(new String[] {"Sophia Martinez", "Online", "72", "QUIZ", "82", "HOMEWORK"});


        // Mock the behavior of readData to return the simulated CSV data
        when(mockCSVReader.readData()).thenReturn(mockCSVData);
    }

    @Test
    public void testParseStudents() throws Exception {

        var studentParser = new StudentParser();

        // Call the method to test
        List<Student> students = studentParser.parseStudents(mockCSVReader.readData());

        // Verify the result
        assertNotNull(students);
        assertEquals(3, students.size());
        assertEquals("Owen Moore", students.getFirst().getName());
        assertEquals("David King", students.get(1).getName());
        assertInstanceOf(OnlineStudent.class, students.get(2));
        assertInstanceOf(PartTimeStudent.class, students.get(1));

        // Verify that the CSVReader's readData method was called
        verify(mockCSVReader).readData();
    }
}