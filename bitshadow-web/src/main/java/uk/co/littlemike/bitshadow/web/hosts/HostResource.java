package uk.co.littlemike.bitshadow.web.hosts;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceService;
import uk.co.littlemike.bitshadow.hosts.HostService;
import uk.co.littlemike.bitshadow.web.appinstances.AppInstanceRepresentation;

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
    private final HostService hostService;
    private final AppInstanceService appInstanceService;

    @Inject
    public HostResource(HostService hostService, AppInstanceService appInstanceService) {
        this.hostService = hostService;
        this.appInstanceService = appInstanceService;
    }

    @GET
    public List<HostRepresentation> getAll() {
        return hostService.getAll().stream()
                .map(HostRepresentation::new)
                .collect(toList());
    }

    @GET
    @Path("/{hostname}")
    public HostRepresentation getByHostname(@PathParam("hostname") String hostname) {
        return new HostRepresentation(hostService.getByHostname(hostname));
    }

    @GET
    @Path("/{hostname}/instances")
    public List<AppInstanceRepresentation> getInstances(@PathParam("hostname") String hostname) {
        return appInstanceService.getByHostname(hostname).stream()
                .map(AppInstanceRepresentation::new)
                .collect(toList());
    }
}
