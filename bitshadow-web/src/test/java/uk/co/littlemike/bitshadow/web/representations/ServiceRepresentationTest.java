package uk.co.littlemike.bitshadow.web.representations;

import org.junit.Test;
import uk.co.littlemike.bitshadow.model.Service;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceRepresentationTest {
    private static final String SERVICE_ID = "Service id";

    @Test
    public void mapsAllFieldsFromDomain() {
        Service service = new Service().withId(SERVICE_ID);

        ServiceRepresentation representation = new ServiceRepresentation(service);

        assertThat(representation.getId()).isEqualTo(SERVICE_ID);
    }
}