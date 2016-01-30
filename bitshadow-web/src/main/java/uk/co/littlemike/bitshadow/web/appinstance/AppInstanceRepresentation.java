package uk.co.littlemike.bitshadow.web.appinstance;

import uk.co.littlemike.bitshadow.appinstance.AppInstance;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppInstanceRepresentation {
    private final String id;
    private final LocalDateTime timeRegistered;

    public AppInstanceRepresentation(AppInstance instance) {
        id = instance.getId();
        timeRegistered = instance.getTimeRegistered();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimeRegistered() {
        return timeRegistered;
    }
}
