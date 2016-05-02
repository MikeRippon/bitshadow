package uk.co.littlemike.bitshadow.client;

import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

import static uk.co.littlemike.bitshadow.client.RegistrationStatus.*;

public class BitshadowService {
    private final BitshadowEndpoint endpoint;
    private final String hostname;
    private final String appName;
    private RegistrationStatus registrationStatus = PENDING;

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
            registrationStatus = FAILED;
        }
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }
}
