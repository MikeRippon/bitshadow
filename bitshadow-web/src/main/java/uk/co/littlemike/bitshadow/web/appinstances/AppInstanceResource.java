package uk.co.littlemike.bitshadow.web.appinstances;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/app-instances")
@Api("App Instances")
@Singleton
public class AppInstanceResource {
    private final AppInstanceService appInstanceService;

    @Inject
    public AppInstanceResource(AppInstanceService appInstanceService) {
        this.appInstanceService = appInstanceService;
    }

    @GET
    @Path("/{id}")
    public AppInstanceRepresentation getById(@PathParam("id") String id) {
        return new AppInstanceRepresentation(appInstanceService.getById(id));
    }

    @PUT
    @Path("/{id}")
    public Response registerAppInstance(@PathParam("id") String id,
                                        @Valid RegisterAppInstanceRepresentation representation) {
        AppInstance instance = appInstanceService.upsert(id, representation);
        return Response.status(CREATED)
                .entity(new AppInstanceRepresentation(instance))
                .build();
    }
}
