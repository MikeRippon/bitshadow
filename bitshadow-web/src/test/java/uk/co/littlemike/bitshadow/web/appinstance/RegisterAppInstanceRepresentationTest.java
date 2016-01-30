package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.app.TestApp;
import uk.co.littlemike.bitshadow.appinstance.TestAppInstance;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAppInstanceRepresentationTest {
    private static final LocalDateTime TIME_REGISTERED = LocalDateTime.now();
    private static final String APP_NAME = "App name";
    private static final String DESCRIPTION = "Description";

    private RegisterAppInstanceRepresentation update;
    private TestAppInstance instance;
    private TestApp app;

    @Before
    public void setUp() throws Exception {
        update = new RegisterAppInstanceRepresentation();
        instance = new TestAppInstance();
        app = new TestApp();
    }

    @Test
    public void appliesUpdatesToAppInstance() {
        update.setTimeRegistered(TIME_REGISTERED);

        update.applyTo(instance);

        assertThat(instance.getTimeRegistered()).isEqualTo(TIME_REGISTERED);
    }

    @Test
    public void appliesUpdatesToApp() {
        update.setAppName(APP_NAME);
        update.setAppDescription(DESCRIPTION);

        update.getAppUpdate().applyTo(app);

        assertThat(update.getAppName()).isEqualTo(APP_NAME);
        assertThat(app.getDescription()).contains(DESCRIPTION);
    }
}