package uk.co.littlemike.bitshadow.client.config;

public class PojoBitshadowConfiguration implements BitshadowConfiguration {
    private final String appName;

    public PojoBitshadowConfiguration(String appName) {
        this.appName = appName;
    }

    @Override
    public String getAppName() {
        return appName;
    }
}
