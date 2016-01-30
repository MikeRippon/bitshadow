package uk.co.littlemike.bitshadow.appinstance;

import uk.co.littlemike.bitshadow.app.App;
import uk.co.littlemike.bitshadow.app.TestApp;

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
