package uk.co.littlemike.bitshadow.web.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.model.Service;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;
import uk.co.littlemike.bitshadow.web.representations.ServiceRepresentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServicesResourceTest {
    private static final String SERVICE_ID = "Service ID";

    @Mock
    private ServicesRepository repository;

    @InjectMocks
    private ServicesResource resource;

    @Test
    public void getsServiceByIdFromRepository() {
        when(repository.getById(SERVICE_ID)).thenReturn(new Service().withId(SERVICE_ID));

        ServiceRepresentation representation = resource.getById(SERVICE_ID);

        assertThat(representation.getId()).isEqualTo(SERVICE_ID);
    }
}