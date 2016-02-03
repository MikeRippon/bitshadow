package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.TestApp;

import java.time.LocalDateTime;

public class TestAppInstance extends AppInstance {

    public TestAppInstance() {
        super("Id", new TestApp());
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
