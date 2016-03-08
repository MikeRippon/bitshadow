package uk.co.littlemike.bitshadow.client;

import java.util.UUID;

public class AppInstance {
    private final String id;
    private final String appName;
    private final String hostname;

    public AppInstance(String appName, String hostname) {
        id = UUID.randomUUID().toString();
        this.appName = appName;
        this.hostname = hostname;
    }

    public String getId() {
        return id;
    }

    public String getAppName() {
        return appName;
    }

    public String getHostname() {
        return hostname;
    }
}
