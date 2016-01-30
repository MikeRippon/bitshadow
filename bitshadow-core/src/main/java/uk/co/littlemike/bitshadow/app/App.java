package uk.co.littlemike.bitshadow.app;

public class App {
    private final String name;
    private String description;

    public App(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
