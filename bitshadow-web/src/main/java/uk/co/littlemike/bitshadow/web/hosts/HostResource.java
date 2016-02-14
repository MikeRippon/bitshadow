package uk.co.littlemike.bitshadow.web.hosts;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.hosts.HostService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Api("Hosts")
@Path("/hosts")
@Singleton
public class HostResource {
    private final HostService service;

    @Inject
    public HostResource(HostService service) {
        this.service = service;
    }

    @GET
    public List<HostRepresentation> getAll() {
        return service.getAll().stream()
                .map(HostRepresentation::new)
                .collect(toList());
    }

    @GET
    @Path("/{hostname}")
    public HostRepresentation getByHostname(@PathParam("hostname") String hostname) {
        return new HostRepresentation(service.getByHostname(hostname));
    }
}
