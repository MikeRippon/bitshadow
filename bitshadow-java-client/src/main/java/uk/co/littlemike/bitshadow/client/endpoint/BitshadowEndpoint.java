package uk.co.littlemike.bitshadow.client.endpoint;

import uk.co.littlemike.bitshadow.client.AppInstance;

public interface BitshadowEndpoint {
    void registerInstance(AppInstance any);

    boolean heartbeat(String instanceId);
}
