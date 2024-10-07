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
    private Assignment _assignment1;
    private Assignment _assignment2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentParser = new StudentParser();

        _assignment1 = new Assignment("Assignment1", 100, "2024-09-30", AssignmentType.EXAM);
        _assignment2 = new Assignment("Assignment2", 100, "2024-09-30", AssignmentType.QUIZ);
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
        assertThat(students.size()).isEqualTo(3);

        // Test FullTime student
        var fullTimeStudent = students.get(0);
        assertThat(fullTimeStudent.getName()).isEqualTo("Owen Moore");
        assertThat(fullTimeStudent.getStudentType()).isEqualTo(StudentType.FULLTIME);
        assertThat(fullTimeStudent.getGrade(_assignment1)).isEqualTo(99);
        assertThat(fullTimeStudent.getGrade(_assignment2)).isEqualTo(84);

        // Test PartTime student
        var partTimeStudent = students.get(1);
        assertThat(partTimeStudent.getName()).isEqualTo("David King");
        assertThat(partTimeStudent.getStudentType()).isEqualTo(StudentType.PARTTIME);
        assertThat(partTimeStudent).isInstanceOf(PartTimeStudent.class);
        assertThat(((PartTimeStudent) partTimeStudent).getHoursWorked()).isEqualTo(11);
        _assignment1.setType(AssignmentType.PROJECT);
        _assignment2.setType(AssignmentType.QUIZ);
        assertThat(partTimeStudent.getGrade(_assignment1)).isEqualTo(73);
        assertThat(partTimeStudent.getGrade(_assignment2)).isEqualTo(93);

        // Test Online student
        var onlineStudent = students.get(2);
        assertThat(onlineStudent.getName()).isEqualTo("Sophia Martinez");
        assertThat(onlineStudent.getStudentType()).isEqualTo(StudentType.ONLINE);
        assertThat(onlineStudent).isInstanceOf(OnlineStudent.class);
        _assignment1.setType(AssignmentType.QUIZ);
        _assignment2.setType(AssignmentType.HOMEWORK);
        assertThat(onlineStudent.getGrade(_assignment1)).isEqualTo(72);
        assertThat(onlineStudent.getGrade(_assignment2)).isEqualTo(82);

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
        var students = studentParser.parseStudents(csvReader.readData());

        // Assertions
        assertThat(students.isEmpty()).isTrue();

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