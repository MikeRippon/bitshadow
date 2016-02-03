package uk.co.littlemike.bitshadow.web.appinstances;

import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.web.apps.AppRepresentation;

import java.time.LocalDateTime;

public class AppInstanceRepresentation {
    private final String id;
    private final LocalDateTime timeRegistered;
    private final AppRepresentation app;

    public AppInstanceRepresentation(AppInstance instance) {
        id = instance.getId();
        timeRegistered = instance.getTimeRegistered();
        app = new AppRepresentation(instance.getApp());
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimeRegistered() {
        return timeRegistered;
    }

    public AppRepresentation getApp() {
        return app;
    }
}
