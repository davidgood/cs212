package org.walsh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class StudentTest {

    private Student student;
    private Assignment assignment1;
    private Assignment assignment2;

    @BeforeEach
    public void setUp() {
        student = new Student("John Doe", "S001");
        assignment1 = mock(Assignment.class);
        assignment2 = mock(Assignment.class);

        // Mock the assignments
        when(assignment1.getName()).thenReturn("Assignment 1");
        when(assignment2.getName()).thenReturn("Assignment 2");
    }

    @Test
    public void testAddGrade() {
        student.addGrade(assignment1, 85);
        assertEquals((Integer) 85, student.getGrade(assignment1));
    }

    @Test
    public void testGetGrade() {
        student.addGrade(assignment1, 85);
        assertEquals((Integer) 85, student.getGrade(assignment1));
        assertNull(student.getGrade(assignment2)); // Should return null as no grade is added for assignment2
    }

    @Test
    public void testCalculateAverageGrade() {
        student.addGrade(assignment1, 85);
        student.addGrade(assignment2, 90);

        double average = student.calculateAverageGrade();
        assertEquals(87.5, average, 0.01);
    }

    @Test
    public void testCalculateAverageGradeNoGrades() {
        double average = student.calculateAverageGrade();
        assertEquals(0.0, average, 0.01);
    }

    @Test
    public void testSimulateAPIPost() {
        student.addGrade(assignment1, 85);
        student.addGrade(assignment2, 90);

        String expectedOutput = "Posting student data to external system:\n" +
                "Student Name: John Doe\n" +
                "Student ID: S001\n" +
                "Assignment: Assignment 1, Grade: 85\n" +
                "Assignment: Assignment 2, Grade: 90\n";

        // Capture the output to verify it matches the expected output
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        student.simulateAPIPost();

        assertEquals(expectedOutput.trim(), out.toString().trim());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void testGetId() {
        assertEquals("S001", student.getId());
    }
}