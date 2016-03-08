package uk.co.littlemike.bitshadow.client;

import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

public class BitshadowService {
    private final BitshadowEndpoint endpoint;
    private final String hostname;
    private final String appName;

    BitshadowService(BitshadowConfiguration config, BitshadowEndpoint endpoint, HostnameResolver hostnameResolver) {
        this.endpoint = endpoint;
        appName = config.getAppName();
        hostname = hostnameResolver.getHostname();
    }

    public void start() {
        endpoint.registerInstance(new AppInstance(appName, hostname));
    }
}
