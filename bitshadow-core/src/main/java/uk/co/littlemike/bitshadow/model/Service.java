package uk.co.littlemike.bitshadow.model;

public class Service {
    private String id;

    public Service withId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }
}
