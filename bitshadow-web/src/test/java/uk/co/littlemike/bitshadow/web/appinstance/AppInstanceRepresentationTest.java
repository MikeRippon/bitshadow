package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AppInstanceRepresentationTest {
    private static final String ID = "Id";
    private static final LocalDateTime TIME_REGISTERED = LocalDateTime.now();

    @Test
    public void mapsFromDomain() {
        AppInstance instance = new AppInstance(ID);
        instance.setTimeRegistered(TIME_REGISTERED);

        AppInstanceRepresentation representation = new AppInstanceRepresentation(instance);

        assertThat(representation.getId()).isEqualTo(ID);
        assertThat(representation.getTimeRegistered()).isEqualTo(TIME_REGISTERED);
    }
}
