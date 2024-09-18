package org.walsh;

public abstract class Person {
    private final String name;
    private final String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract void simulateAPIPost();
}