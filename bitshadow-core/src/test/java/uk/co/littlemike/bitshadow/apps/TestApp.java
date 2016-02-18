package uk.co.littlemike.bitshadow.apps;

public class TestApp extends App {

    public TestApp() {
        super("App name");
    }

    public TestApp withName(String name) {
        this.name = name;
        return this;
    }
}
