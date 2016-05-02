package uk.co.littlemike.bitshadow.client.config;

public class PojoBitshadowConfiguration implements BitshadowConfiguration {
    private final String appName;
    private final String bitshadowHost;

    public PojoBitshadowConfiguration(String appName, String bitshadowHost) {
        this.appName = appName;
        this.bitshadowHost = bitshadowHost;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public String getBitshadowHost() {
        return bitshadowHost;
    }
}
