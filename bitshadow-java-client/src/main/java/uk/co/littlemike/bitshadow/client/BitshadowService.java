package uk.co.littlemike.bitshadow.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowRestEndpoint;

import java.util.UUID;
import java.util.concurrent.Executors;

import static uk.co.littlemike.bitshadow.client.RegistrationStatus.*;

public class BitshadowService {
    private final Logger LOG = LoggerFactory.getLogger(BitshadowService.class);
    private final BitshadowEndpoint endpoint;
    private final String hostname;
    private final Heartbeat heartbeat;
    private final String appName;
    private RegistrationStatus registrationStatus = PENDING;

    public BitshadowService(BitshadowConfiguration config) {
        this(config,
             new BitshadowRestEndpoint(config.getBitshadowHost()),
             new HostnameResolver(config));
    }

    private BitshadowService(BitshadowConfiguration config,
                             BitshadowRestEndpoint endpoint,
                             HostnameResolver hostnameResolver) {
        this(config, endpoint, hostnameResolver, new Heartbeat(Executors.newScheduledThreadPool(1), endpoint, config));
    }

    BitshadowService(BitshadowConfiguration config,
                     BitshadowEndpoint endpoint,
                     HostnameResolver hostnameResolver,
                     Heartbeat heartbeat) {
        this.endpoint = endpoint;
        appName = config.getAppName();
        hostname = hostnameResolver.getHostname();
        this.heartbeat = heartbeat;
    }

    public void start() {
        try {
            String id = UUID.randomUUID().toString();
            endpoint.registerInstance(new AppInstance(id, appName, hostname));
            heartbeat.start(id);
            registrationStatus = REGISTERED;
        } catch (Exception e) {
            LOG.error("Failed to register app instance with bitshadow service", e);
            registrationStatus = FAILED;
        }
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void stop() {
        heartbeat.stop();
    }

    public HeartbeatStatus getHeartbeatStatus() {
        return heartbeat.getStatus();
    }
}
