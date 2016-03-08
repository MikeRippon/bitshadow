package uk.co.littlemike.bitshadow.client;

import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

public class BitshadowService {
    private final BitshadowConfiguration config;
    private final BitshadowEndpoint endpoint;

    BitshadowService(BitshadowConfiguration config, BitshadowEndpoint endpoint) {
        this.config = config;
        this.endpoint = endpoint;
    }

    public void start() {
        endpoint.registerInstance(new AppInstance(config.getAppName(), config.getHostname()));
    }
}
