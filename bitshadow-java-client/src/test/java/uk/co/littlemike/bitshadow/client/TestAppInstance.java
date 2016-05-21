package uk.co.littlemike.bitshadow.client;

import static uk.co.littlemike.bitshadow.client.TestObject.uuid;

public class TestAppInstance extends AppInstance implements TestObject {
    public TestAppInstance() {
        super(uuid(), "App-" + uuid(), "Host-" + uuid());
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
