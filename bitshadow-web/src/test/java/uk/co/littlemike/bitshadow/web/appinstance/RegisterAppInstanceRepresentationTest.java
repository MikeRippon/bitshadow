package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAppInstanceRepresentationTest {
    private static final LocalDateTime TIME_REGISTERED = LocalDateTime.now();

    private RegisterAppInstanceRepresentation update;
    private AppInstance instance;

    @Before
    public void setUp() throws Exception {
        update = new RegisterAppInstanceRepresentation();
        instance = new AppInstance("ID");
    }

    @Test
    public void appliesUpdatesToAppInstance() {
        update.setTimeRegistered(TIME_REGISTERED);

        update.applyTo(instance);

        assertThat(instance.getTimeRegistered()).isEqualTo(TIME_REGISTERED);
    }
}