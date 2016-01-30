package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Test;
import uk.co.littlemike.bitshadow.app.TestApp;
import uk.co.littlemike.bitshadow.appinstance.TestAppInstance;
import uk.co.littlemike.bitshadow.web.app.AppRepresentation;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AppInstanceRepresentationTest {
    private static final String ID = "Id";
    private static final LocalDateTime TIME_REGISTERED = LocalDateTime.now();

    @Test
    public void mapsFromDomain() {
        TestAppInstance instance = new TestAppInstance()
                .withId(ID)
                .withApp(new TestApp())
                .withTimeRegistered(TIME_REGISTERED);

        AppInstanceRepresentation representation = new AppInstanceRepresentation(instance);

        assertThat(representation.getId()).isEqualTo(ID);
        assertThat(representation.getTimeRegistered()).isEqualTo(TIME_REGISTERED);
        assertThat(representation.getApp()).isInstanceOf(AppRepresentation.class);
    }
}
