package uk.co.littlemike.bitshadow.client.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.client.BitshadowService;
import uk.co.littlemike.bitshadow.client.HeartbeatStatus;
import uk.co.littlemike.bitshadow.client.RegistrationStatus;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

public class BitshadowServiceTest {
    private static final int HEARTBEAT_INTERVAL_MILLIS = 500;
    private BitshadowService service;

    @Before
    public void setUp() throws Exception {
        PojoBitshadowConfiguration config = new PojoBitshadowConfiguration("My app", "http://localhost:8080")
                .withHeartbeatIntervalMillis(HEARTBEAT_INTERVAL_MILLIS);
        service = new BitshadowService(config);
    }

    @After
    public void tearDown() {
        service.stop();
    }

    @Test
    public void shouldRegisterAppInstance() {
        service.start();

        assertThat(service.getRegistrationStatus()).isEqualTo(RegistrationStatus.REGISTERED);
    }

    @Test
    public void shouldHaveHeartbeat() throws InterruptedException {
        service.start();

        Thread.sleep(HEARTBEAT_INTERVAL_MILLIS * 4);

        assertThat(service.getHeartbeatStatus()).isEqualTo(HeartbeatStatus.OK);
    }
}
