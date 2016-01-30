package uk.co.littlemike.bitshadow.app;

import java.util.Optional;

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

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
