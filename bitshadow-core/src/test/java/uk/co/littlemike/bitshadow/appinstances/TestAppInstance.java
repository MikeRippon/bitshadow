package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.TestApp;
import uk.co.littlemike.bitshadow.hosts.TestHost;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestAppInstance extends AppInstance {

    public TestAppInstance() {
        super(UUID.randomUUID().toString(), new TestApp(), new TestHost());
    }

    public TestAppInstance withId(String id) {
        this.id = id;
        return this;
    }

    public TestAppInstance withApp(App app) {
        this.app = app;
        return this;
    }

    public TestAppInstance withTimeRegistered(LocalDateTime timeRegistered) {
        setTimeRegistered(timeRegistered);
        return this;
    }
}
