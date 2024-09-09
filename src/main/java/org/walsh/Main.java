package org.walsh;

import java.util.ArrayList;
import java.util.Scanner;
import org.walsh.hw3.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for employee " + (i + 1) + ": ");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Hourly Wage: ");
            double hourlyWage = scanner.nextDouble();
            System.out.print("Hours Worked: ");
            double hoursWorked = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            Employee emp = new Employee(name, hourlyWage, hoursWorked);
            employees.add(emp);

            double salary = emp.calculateWeeklySalary();
            Company.addToTotalPayroll(salary);
            emp.displayEmployeeDetails();
            System.out.println();
        }

        System.out.println("Total Employees: " + Employee.getTotalEmployees());
        Company.displayTotalPayroll();
    }


}