package uk.co.littlemike.bitshadow.web.appinstances;

import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.web.apps.AppRepresentation;
import uk.co.littlemike.bitshadow.web.hosts.HostRepresentation;

import java.time.Instant;

public class AppInstanceRepresentation {
    private final String id;
    private final Instant timeRegistered;
    private final AppRepresentation app;
    private final HostRepresentation host;

    public AppInstanceRepresentation(AppInstance instance) {
        id = instance.getId();
        timeRegistered = instance.getTimeRegistered();
        app = new AppRepresentation(instance.getApp());
        host = new HostRepresentation(instance.getHost());
    }

    public String getId() {
        return id;
    }

    public Instant getTimeRegistered() {
        return timeRegistered;
    }

    public AppRepresentation getApp() {
        return app;
    }

    public HostRepresentation getHost() {
        return host;
    }
}
