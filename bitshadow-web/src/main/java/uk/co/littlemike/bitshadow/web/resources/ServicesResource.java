package uk.co.littlemike.bitshadow.web.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import uk.co.littlemike.bitshadow.model.Service;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;
import uk.co.littlemike.bitshadow.web.representations.ServiceRepresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/services")
@Api("Services")
public class ServicesResource {
    private final ServicesRepository servicesRepository;

    @Inject
    public ServicesResource(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Get a service by id", response = ServiceRepresentation.class)
    public ServiceRepresentation getById(@PathParam("id") String id) {
        Service service = servicesRepository.getById(id);
        return new ServiceRepresentation(service);
    }
}
