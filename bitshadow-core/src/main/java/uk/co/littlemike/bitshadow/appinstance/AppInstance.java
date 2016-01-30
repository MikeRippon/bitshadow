package uk.co.littlemike.bitshadow.appinstance;

import java.time.LocalDateTime;

public class AppInstance {
    private final String id;
    private LocalDateTime timeRegistered;

    public AppInstance(String id) {
        this.id = id;
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
}
