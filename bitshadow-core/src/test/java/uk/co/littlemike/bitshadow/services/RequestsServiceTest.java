package uk.co.littlemike.bitshadow.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RequestsServiceTest {
    public static final String SERVICE_ID = "Service ID";

    @Mock
    private ServicesRepository servicesRepository;

    @InjectMocks
    private RequestsService service;

    @Test
    public void createsServiceOnInboundRequest() {
        service.createRequest(SERVICE_ID);

        verify(servicesRepository).ensureServiceExists(SERVICE_ID);
    }
}