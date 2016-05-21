package uk.co.littlemike.bitshadow.client.endpoint;

import uk.co.littlemike.bitshadow.client.AppInstance;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BitshadowRestEndpoint implements BitshadowEndpoint {
    private final WebTarget endpoint;

    public BitshadowRestEndpoint(String host) {
        endpoint = ClientBuilder.newClient().target(host);
    }

    @Override
    public void registerInstance(AppInstance appInstance) {
        Response response = endpoint.path("app-instances")
                .path(appInstance.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(appInstance, MediaType.APPLICATION_JSON_TYPE));
        if (response.getStatus() >= 300) {
            throw new BitshadowEndpointException(
                    String.format("Unable to register application instance at %s/app-instances, response code: %s, response body: %s",
                            endpoint.getUri(),
                            response.getStatus(),
                            response.readEntity(String.class)
                    ));
        }
    }

    @Override
    public boolean heartbeat(String instanceId) {
        int status = endpoint.path("app-instances")
                .path(instanceId)
                .path("status")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity("", MediaType.APPLICATION_JSON_TYPE))
                .getStatus();
        return status < 300;
    }
}
