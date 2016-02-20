package uk.co.littlemike.bitshadow.apps;

import java.util.UUID;

public class TestApp extends App {

    public TestApp() {
        super("App " + UUID.randomUUID().toString());
    }

    public TestApp withName(String name) {
        this.name = name;
        return this;
    }
}
