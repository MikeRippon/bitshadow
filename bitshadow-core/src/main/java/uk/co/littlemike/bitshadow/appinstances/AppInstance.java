package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;

import java.time.LocalDateTime;

public class AppInstance {
    protected String id;
    private LocalDateTime timeRegistered;
    protected App app;

    public AppInstance(String id, App app) {
        this.id = id;
        this.app = app;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(LocalDateTime timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
