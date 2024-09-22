package org.walsh;

public class Assignment implements IGradable {
    private final String _name;
    private final int _maxPoints;
    private final String _dueDate;

    public Assignment(String name, int maxPoints, String dueDate) {
        _name = name;
        _maxPoints = maxPoints;
        _dueDate = dueDate;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public int getMaxPoints() {
        return _maxPoints;
    }

    @Override
    public String getDueDate() {
        return _dueDate;
    }
}