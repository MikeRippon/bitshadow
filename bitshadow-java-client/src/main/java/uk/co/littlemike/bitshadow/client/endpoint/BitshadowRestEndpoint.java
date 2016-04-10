package uk.co.littlemike.bitshadow.client.endpoint;

import uk.co.littlemike.bitshadow.client.AppInstance;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class BitshadowRestEndpoint implements BitshadowEndpoint {
    private final WebTarget endpoint;

    public BitshadowRestEndpoint(String host) {
        endpoint = ClientBuilder.newClient().target(host);
    }

    @Override
    public void registerInstance(AppInstance appInstance) {
        endpoint.path("app-instances")
                .path(appInstance.getId())
                .request()
                .put(Entity.entity(appInstance, MediaType.APPLICATION_JSON_TYPE));
    }
}
