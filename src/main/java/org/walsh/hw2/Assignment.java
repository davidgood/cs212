package org.walsh.hw2;

public class Assignment {
    private final String name;
    private final int maxPoints;
    private final String dueDate;

    public Assignment(String name, int maxPoints, String dueDate) {
        this.name = name;
        this.maxPoints = maxPoints;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public String getDueDate() {
        return dueDate;
    }
}