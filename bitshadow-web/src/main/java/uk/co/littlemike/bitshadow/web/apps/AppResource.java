package uk.co.littlemike.bitshadow.web.apps;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceService;
import uk.co.littlemike.bitshadow.apps.AppService;
import uk.co.littlemike.bitshadow.web.appinstances.AppInstanceRepresentation;

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
    private final AppService appService;
    private final AppInstanceService appInstanceService;

    @Inject
    public AppResource(AppService appService, AppInstanceService appInstanceService) {
        this.appService = appService;
        this.appInstanceService = appInstanceService;
    }

    @GET
    public List<AppRepresentation> getAll() {
        return appService.getAll().stream()
                .map(AppRepresentation::new)
                .collect(toList());
    }

    @GET
    @Path("/{name}")
    public AppRepresentation getByName(@PathParam("name") String name) {
        return new AppRepresentation(appService.getByName(name));
    }

    @GET
    @Path("/{name}/instances")
    public List<AppInstanceRepresentation> getInstances(@PathParam("name") String name) {
        return appInstanceService.getByAppName(name).stream()
                .map(AppInstanceRepresentation::new)
                .collect(toList());
    }
}
