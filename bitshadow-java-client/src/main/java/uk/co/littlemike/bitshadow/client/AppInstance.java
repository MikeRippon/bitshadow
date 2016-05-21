package uk.co.littlemike.bitshadow.client;

public class AppInstance {
    String id;
    String appName;
    String hostname;

    public AppInstance(String id, String appName, String hostname) {
        this.id = id;
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
