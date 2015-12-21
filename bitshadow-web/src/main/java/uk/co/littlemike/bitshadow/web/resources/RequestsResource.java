package uk.co.littlemike.bitshadow.web.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import uk.co.littlemike.bitshadow.services.RequestsService;
import uk.co.littlemike.bitshadow.web.representations.InboundRequestRepresentation;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/requests")
@Api("Requests")
public class RequestsResource {
    private final RequestsService requestsService;

    @Inject
    public RequestsResource(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @POST
    @Path("inbound")
    @ApiOperation(value = "Post an inbount request")
    public void postInbound(@Valid InboundRequestRepresentation request) {
        requestsService.createRequest(request.getServiceId());
    }
}
