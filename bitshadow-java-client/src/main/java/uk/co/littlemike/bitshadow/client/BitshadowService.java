package uk.co.littlemike.bitshadow.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowRestEndpoint;

import static uk.co.littlemike.bitshadow.client.RegistrationStatus.*;

public class BitshadowService {
    private final Logger LOG = LoggerFactory.getLogger(BitshadowService.class);
    private final BitshadowEndpoint endpoint;
    private final String hostname;
    private final String appName;
    private RegistrationStatus registrationStatus = PENDING;

    public BitshadowService(BitshadowConfiguration config) {
        this(config, new BitshadowRestEndpoint(config.getBitshadowHost()), new HostnameResolver(config));
    }

    BitshadowService(BitshadowConfiguration config, BitshadowEndpoint endpoint, HostnameResolver hostnameResolver) {
        this.endpoint = endpoint;
        appName = config.getAppName();
        hostname = hostnameResolver.getHostname();
    }

    public void start() {
        try {
            endpoint.registerInstance(new AppInstance(appName, hostname));
            registrationStatus = REGISTERED;
        } catch (Exception e) {
            LOG.error("Failed to register app instance with bitshadow service", e);
            registrationStatus = FAILED;
        }
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }
}
