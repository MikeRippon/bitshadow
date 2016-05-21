package uk.co.littlemike.bitshadow.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Heartbeat {
    private final Logger LOG = LoggerFactory.getLogger(Heartbeat.class);

    private final ScheduledExecutorService scheduler;
    private final BitshadowEndpoint endpoint;
    private final long heartbeatInterval;

    private volatile HeartbeatStatus status = HeartbeatStatus.PENDING;

    public Heartbeat(ScheduledExecutorService scheduler, BitshadowEndpoint endpoint, BitshadowConfiguration config) {
        this.scheduler = scheduler;
        this.endpoint = endpoint;
        heartbeatInterval = config.getHeartbeatIntervalMillis();
    }

    public void start(String instanceId) {
        scheduler.schedule(() -> {
            try {
                if (endpoint.heartbeat(instanceId)) {
                    status = HeartbeatStatus.OK;
                } else {
                    LOG.warn("Heartbeat failed");
                    status = HeartbeatStatus.FAILED;
                }
            } catch (Exception e) {
                LOG.warn("Heartbeat failed", e);
                status = HeartbeatStatus.FAILED;
            }
        }, heartbeatInterval, TimeUnit.MILLISECONDS);
    }

    public HeartbeatStatus getStatus() {
        return status;
    }

    public void stop() {
        scheduler.shutdownNow();
    }
}
