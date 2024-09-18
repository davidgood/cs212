package org.walsh;

public abstract class Person {
    private final String _name;
    private final String _id;

    public Person(String name, String id) {
        _name = name;
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public String getId() {
        return _id;
    }

    public abstract void simulateAPIPost();
}