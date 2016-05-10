package uk.co.littlemike.bitshadow.web.appinstances;

import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstances.TestAppInstance;
import uk.co.littlemike.bitshadow.apps.TestApp;
import uk.co.littlemike.bitshadow.hosts.TestHost;
import uk.co.littlemike.bitshadow.web.apps.AppRepresentation;
import uk.co.littlemike.bitshadow.web.hosts.HostRepresentation;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class AppInstanceRepresentationTest {
    private static final String ID = "Id";
    private static final Instant TIME_REGISTERED = Instant.now();

    @Test
    public void mapsFromDomain() {
        TestAppInstance instance = new TestAppInstance()
                .withId(ID)
                .withApp(new TestApp())
                .withHost(new TestHost())
                .withTimeRegistered(TIME_REGISTERED);

        AppInstanceRepresentation representation = new AppInstanceRepresentation(instance);

        assertThat(representation.getId()).isEqualTo(ID);
        assertThat(representation.getTimeRegistered()).isEqualTo(TIME_REGISTERED);
        assertThat(representation.getApp()).isInstanceOf(AppRepresentation.class);
        assertThat(representation.getHost()).isInstanceOf(HostRepresentation.class);
    }
}
