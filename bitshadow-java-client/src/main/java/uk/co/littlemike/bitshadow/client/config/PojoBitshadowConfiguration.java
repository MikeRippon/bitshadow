package uk.co.littlemike.bitshadow.client.config;

public class PojoBitshadowConfiguration implements BitshadowConfiguration {
    private final String appName;
    private final String bitshadowHost;
    private long heartbeatIntervalMillis;

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

    @Override
    public long getHeartbeatIntervalMillis() {
        return heartbeatIntervalMillis;
    }

    public PojoBitshadowConfiguration withHeartbeatIntervalMillis(long heartbeatIntervalMillis) {
        this.heartbeatIntervalMillis = heartbeatIntervalMillis;
        return this;
    }
}
