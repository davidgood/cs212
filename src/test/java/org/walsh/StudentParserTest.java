package org.walsh;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class StudentParserTest {

    @Mock
    private CSVReader csvReader;

    private StudentParser studentParser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentParser = new StudentParser();
    }

    @Test
    void parse_students_should_succeed() {
        // Prepare mock data based on the actual CSV structure
        var mockCsvData = getMockCsvData();

        // Set up mock behavior
        when(csvReader.readData()).thenReturn(mockCsvData);

        // Call the method under test
        List<Student> students = studentParser.parseStudents(csvReader.readData());

        // Assertions
        assertEquals(3, students.size());

        // Test FullTime student
        Student fullTimeStudent = students.get(0);
        assertThat(fullTimeStudent.getName()).isEqualTo("Owen Moore");
        assertEquals(StudentType.FULLTIME, fullTimeStudent.getStudentType());
        assertEquals(99, fullTimeStudent.getGrade(new Assignment("Assignment1", 100, "2024-09-30", AssignmentType.EXAM)));
        assertEquals(84, fullTimeStudent.getGrade(new Assignment("Assignment2", 100, "2024-09-30", AssignmentType.QUIZ)));

        // Test PartTime student
        Student partTimeStudent = students.get(1);
        assertEquals("David King", partTimeStudent.getName());
        assertEquals(StudentType.PARTTIME, partTimeStudent.getStudentType());
        assertInstanceOf(PartTimeStudent.class, partTimeStudent);
        assertEquals(11, ((PartTimeStudent) partTimeStudent).getHoursWorked());
        assertEquals(73, partTimeStudent.getGrade(new Assignment("Assignment1", 100, "2024-09-30", AssignmentType.PROJECT)));
        assertEquals(93, partTimeStudent.getGrade(new Assignment("Assignment2", 100, "2024-09-30", AssignmentType.QUIZ)));

        // Test Online student
        Student onlineStudent = students.get(2);
        assertEquals("Sophia Martinez", onlineStudent.getName());
        assertEquals(StudentType.ONLINE, onlineStudent.getStudentType());
        assertInstanceOf(OnlineStudent.class, onlineStudent);
        assertEquals(72, onlineStudent.getGrade(new Assignment("Assignment1", 100, "2024-09-30", AssignmentType.QUIZ)));
        assertEquals(82, onlineStudent.getGrade(new Assignment("Assignment2", 100, "2024-09-30", AssignmentType.HOMEWORK)));

        // Verify that CSVReader's readData method was called
        verify(csvReader, times(1)).readData();
    }

    @Test
    void calculate_average_grade_should_succeed() {
        var mockCsvData = getMockCsvData();

        when(csvReader.readData()).thenReturn(mockCsvData);

        var students = studentParser.parseStudents(csvReader.readData());
        Student student = students.get(0);

        assertThat(student.calculateAverageGrade()).isCloseTo(91.5, offset(0.01));
    }

    @Test
    void parse_students_with_empty_data_should_succeed() {
        // Prepare empty mock data
        var emptyMockData = new ArrayList<String[]>();

        // Set up mock behavior
        when(csvReader.readData()).thenReturn(emptyMockData);

        // Call the method under test
        List<Student> students = studentParser.parseStudents(csvReader.readData());

        // Assertions
        assertTrue(students.isEmpty());

        // Verify that CSVReader's readData method was called
        verify(csvReader, times(1)).readData();
    }

    @Test
    void parse_students_should_fail() {
        // Prepare mock data with invalid entries
        var mockCsvData = new ArrayList<String[]>();
        mockCsvData.add(new String[]{"Name", "Type", "Assignment1", "AssignmentType1", "Assignment2", "AssignmentType2", "HoursWorkedPerWeek"});
        mockCsvData.add(new String[]{"Invalid Student", "InvalidType", "NotANumber", "EXAM", "90", "QUIZ", ""});

        when(csvReader.readData()).thenReturn(mockCsvData);

        // We expect the parser to handle invalid data gracefully
        // This might mean skipping invalid entries or throwing a specific exception
        assertDoesNotThrow(() -> studentParser.parseStudents(csvReader.readData()));
    }

    private static ArrayList<String[]> getMockCsvData() {
        ArrayList<String[]> mockCsvData = new ArrayList<>();
        mockCsvData.add(new String[]{"Name", "Type", "Assignment1", "AssignmentType1", "Assignment2", "AssignmentType2", "HoursWorkedPerWeek"});
        mockCsvData.add(new String[]{"Owen Moore", "FullTime", "99", "EXAM", "84", "QUIZ", ""});
        mockCsvData.add(new String[]{"David King", "PartTime", "73", "PROJECT", "93", "QUIZ", "11"});
        mockCsvData.add(new String[]{"Sophia Martinez", "Online", "72", "QUIZ", "82", "HOMEWORK", ""});
        return mockCsvData;
    }
}