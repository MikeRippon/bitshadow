package uk.co.littlemike.bitshadow.web.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.services.RequestsService;
import uk.co.littlemike.bitshadow.web.representations.InboundRequestRepresentation;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RequestsResourceTest {
    private static final String SERVICE_ID = "Service ID";

    @Mock
    private RequestsService requestsService;

    @InjectMocks
    private RequestsResource resource;

    @Test
    public void createsInboundRequestOnPost() {
        InboundRequestRepresentation inboundRequest = new InboundRequestRepresentation()
                .withServiceId(SERVICE_ID);

        resource.postInbound(inboundRequest);

        verify(requestsService).createRequest(SERVICE_ID);
    }
}