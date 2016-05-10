package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.hosts.Host;

import java.time.Instant;
import java.time.LocalDateTime;

public class AppInstance {
    String id;
    private LocalDateTime timeRegistered;
    App app;
    Host host;
    Instant lastUpdated;

    public AppInstance(String id, App app, Host host) {
        this.id = id;
        this.app = app;
        this.host = host;
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

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
