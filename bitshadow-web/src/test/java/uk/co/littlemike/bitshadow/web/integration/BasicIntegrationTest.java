package uk.co.littlemike.bitshadow.web.integration;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.littlemike.bitshadow.web.BitShadowWebService;
import uk.co.littlemike.bitshadow.web.config.BitShadowConfiguration;

public class BasicIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<BitShadowConfiguration> BITSHADOW_SERVER
            = new DropwizardAppRule<>(BitShadowWebService.class);

    @Test
    public void serverStartsUpOk() {
    }
}
