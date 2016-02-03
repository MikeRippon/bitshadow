package uk.co.littlemike.bitshadow.web.apps;

import uk.co.littlemike.bitshadow.apps.App;

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
