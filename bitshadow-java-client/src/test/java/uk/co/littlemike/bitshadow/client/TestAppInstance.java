package uk.co.littlemike.bitshadow.client;

import java.util.UUID;

public class TestAppInstance extends AppInstance {
    public TestAppInstance() {
        super("App-" + uuid(), "Host-" + uuid());
    }

    private static String uuid() {
        return UUID.randomUUID().toString();
    }

    public TestAppInstance withId(String id) {
        this.id = id;
        return this;
    }

    public TestAppInstance withAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public TestAppInstance withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }
}
