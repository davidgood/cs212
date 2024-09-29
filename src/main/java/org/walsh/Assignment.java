package org.walsh;

public class Assignment implements IGradable {
    private final String _name;
    private final int _maxPoints;
    private final String _dueDate;
    private AssignmentType _type;

    public Assignment(String name, int maxPoints, String dueDate, AssignmentType type) {
        _name = name;
        _maxPoints = maxPoints;
        _dueDate = dueDate;
        _type = type;
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

    public AssignmentType getType() {
        return _type;
    }

    public void setType(AssignmentType type) {
        _type = type;
    }
}