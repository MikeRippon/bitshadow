package uk.co.littlemike.bitshadow.client.integration;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.client.BitshadowService;
import uk.co.littlemike.bitshadow.client.RegistrationStatus;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

public class BitshadowServiceTest {
    private BitshadowService service;

    @Before
    public void setUp() throws Exception {
        PojoBitshadowConfiguration config = new PojoBitshadowConfiguration("My app", "http://localhost:8080");
        service = new BitshadowService(config);
    }

    @Test
    public void shouldRegisterAppInstance() {
        service.start();

        assertThat(service.getRegistrationStatus()).isEqualTo(RegistrationStatus.REGISTERED);
    }
}
