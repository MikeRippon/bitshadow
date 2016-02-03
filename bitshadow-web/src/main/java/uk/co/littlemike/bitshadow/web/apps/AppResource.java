package uk.co.littlemike.bitshadow.web.apps;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.apps.AppService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Api("Apps")
@Path("/apps")
@Singleton
public class AppResource {
    private final AppService service;

    @Inject
    public AppResource(AppService service) {
        this.service = service;
    }

    @GET
    public List<AppRepresentation> getAll() {
        return service.getAll().stream()
                .map(AppRepresentation::new)
                .collect(toList());
    }

    @GET
    @Path("/{id}")
    public AppRepresentation getByName(@PathParam("id") String name) {
        return new AppRepresentation(service.getByName(name));
    }
}
