import ch.qos.logback.classic.Level;
import io.dropwizard.logging.DefaultLoggingFactory;
import io.dropwizard.logging.LoggingFactory;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import uk.co.littlemike.bitshadow.client.integration.BitshadowServiceTest;
import uk.co.littlemike.bitshadow.web.BitShadowWebService;
import uk.co.littlemike.bitshadow.web.config.BitShadowConfiguration;

public class BitshadowServiceTestRunner extends BitshadowServiceTest {

    @ClassRule
    public static final DropwizardAppRule<BitShadowConfiguration> BITSHADOW_SERVER
            = new DropwizardAppRule<>(BitShadowWebService.class);

    @BeforeClass
    public static void setUpServer() {
        DefaultLoggingFactory loggingFactory = (DefaultLoggingFactory) BITSHADOW_SERVER.getTestSupport().getConfiguration().getLoggingFactory();
        loggingFactory.setLevel(Level.DEBUG);
    }
}
