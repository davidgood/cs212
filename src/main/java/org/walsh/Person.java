package org.walsh;

public abstract class Person {
    private final String _name;
    private final String _id;
    protected final String API_URL = "http://localhost:8080/students";
    protected String JSON_PAYLOAD;

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