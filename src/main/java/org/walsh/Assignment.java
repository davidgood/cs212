package org.walsh;

public class Assignment implements IGradable {
    private final String _name;
    private final int _maxPoints;
    private final String _dueDate;
    private AssignmentType _type;

    public Assignment(String name, int maxPoints, String dueDate) {
        _name = name;
        _maxPoints = maxPoints;
        _dueDate = dueDate;
        // TODO - fix this constructor
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

    // TODO - need a way to get assignment type

    // TODO - need a way to set assignment type
}