package org.walsh;

public class PartTimeStudent extends Student {
    private int _hoursWorkedPerWeek;

    public PartTimeStudent(String name, String id, int hoursWorkedPerWeek) {
        super(name, id, StudentType.PARTTIME);  // Use enum to specify student type
        _hoursWorkedPerWeek = hoursWorkedPerWeek;
    }

    public double calculateWorkload() {
        return _hoursWorkedPerWeek + calculateAverageGrade();
    }

    public double getHoursWorked() {
        return _hoursWorkedPerWeek;
    }

    @Override
    public void simulateAPIPost() {
        super.simulateAPIPost();
        System.out.println("Hours Worked Per Week: " + _hoursWorkedPerWeek);
        System.out.println("Workload: " + calculateWorkload());
    }
}