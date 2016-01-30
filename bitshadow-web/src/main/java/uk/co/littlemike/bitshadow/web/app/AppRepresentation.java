package uk.co.littlemike.bitshadow.web.app;

import uk.co.littlemike.bitshadow.app.App;

public class AppRepresentation {
    private final String name;
    private final String description;

    public AppRepresentation(App app) {
        this.name = app.getName();
        this.description = app.getDescription().orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
