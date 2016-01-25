package uk.co.littlemike.bitshadow.web.appinstance;

import io.swagger.annotations.Api;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceService;
import uk.co.littlemike.bitshadow.web.appinstance.AppInstanceRepresentation;

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

    public final AppInstanceService appInstanceService;

    @Inject
    public AppInstanceResource(AppInstanceService appInstanceService) {
        this.appInstanceService = appInstanceService;
    }

    @Path("/{id}")
    @GET
    public AppInstanceRepresentation getById(@PathParam("id") String id) {
        return new AppInstanceRepresentation(appInstanceService.getById(id));
    }

    @PUT
    public Response registerAppInstance(@Valid AppInstanceRepresentation representation) {
        AppInstance instance = appInstanceService.registerAppInstance(representation.toDomain());
        return Response.status(CREATED)
                .entity(new AppInstanceRepresentation(instance))
                .build();
    }
}
