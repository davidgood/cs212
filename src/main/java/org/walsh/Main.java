package org.walsh;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input and store grades
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        int[] grades = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
            while (grades[i] < 0 || grades[i] > 100) {
                System.out.println("Please enter a valid grade between 0 and 100.");
                System.out.print("Enter grade for student " + (i + 1) + ": ");
                grades[i] = scanner.nextInt();
            }
        }

        // Step 2: Calculate average grade
        double average = calculateAverage(grades);
        System.out.println("\nAverage grade: " + average);

        // Step 3: Find highest and lowest grades
        int highest = findHighestGrade(grades);
        int lowest = findLowestGrade(grades);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);

        // Step 4: Sort the grades
        sortGrades(grades);
        System.out.print("Grades sorted: ");
        for (int grade : grades) {
            System.out.print(grade + " ");
        }
        System.out.println();
    }

    // Method to calculate the average grade
    public static double calculateAverage(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    // Method to find the highest grade
    public static int findHighestGrade(int[] grades) {
        int highest = grades[0];
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public static int findHighestGradeStreams(int[] grades) {
        var max = Arrays.stream(grades).max();
        return max.isPresent() ? max.getAsInt() : 100;
    }

    // Method to find the lowest grade
    public static int findLowestGrade(int[] grades) {
        int lowest = grades[0];
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static int findLowestGradeStreams(int[] grades) {
        var min = Arrays.stream(grades).max();
        return min.isPresent() ? min.getAsInt() : 0;
    }

    // Method to sort the grades using bubble sort algorithm
    public static void sortGrades(int[] grades) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < grades.length - 1; i++) {
                if (grades[i] > grades[i + 1]) {
                    // Swap grades[i] and grades[i + 1]
                    int temp = grades[i];
                    grades[i] = grades[i + 1];
                    grades[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void sortGradesStreaming(int[] grades) {
        Arrays.sort(grades);
    }
}