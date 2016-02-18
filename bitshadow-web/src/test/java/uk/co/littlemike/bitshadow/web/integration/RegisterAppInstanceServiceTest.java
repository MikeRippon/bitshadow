package uk.co.littlemike.bitshadow.web.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.littlemike.bitshadow.web.BitShadowWebService;
import uk.co.littlemike.bitshadow.web.appinstances.RegisterAppInstanceRepresentation;
import uk.co.littlemike.bitshadow.web.config.BitShadowConfiguration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAppInstanceServiceTest {

    @ClassRule
    public static final DropwizardAppRule<BitShadowConfiguration> BITSHADOW_SERVER
            = new DropwizardAppRule<>(BitShadowWebService.class);

    @Test
    public void registerAppInstanceCreatesAppAndHost() {
        String instanceId = java.util.UUID.randomUUID().toString();
        String appName = "My app";
        String hostname = "hostname";

        int status = registerInstance(instanceId, appName, hostname);
        assertThat(status).isEqualTo(201);

        JsonNode app = getApp(appName);
        assertThat(app.path("name").textValue())
                .as(app.toString())
                .isEqualTo(appName);

        JsonNode host = getHost(hostname);
        assertThat(host.path("hostname").textValue())
                .as(host.toString())
                .isEqualTo(hostname);
    }

    private int registerInstance(String instanceId, String appName, String hostname) {
        RegisterAppInstanceRepresentation registerInstance = new RegisterAppInstanceRepresentation();
        registerInstance.setAppName(appName);
        registerInstance.setHostname(hostname);

        return client().path("app-instances")
                .path(instanceId)
                .request(APPLICATION_JSON_TYPE)
                .put(Entity.entity(registerInstance, APPLICATION_JSON_TYPE))
                .getStatus();
    }

    private JsonNode getApp(String appName) {
        return client().path("apps")
                .path(appName)
                .request(APPLICATION_JSON_TYPE)
                .get()
                .readEntity(JsonNode.class);
    }

    private JsonNode getHost(String hostname) {
        return client().path("hosts")
                .path(hostname)
                .request(APPLICATION_JSON_TYPE)
                .get()
                .readEntity(JsonNode.class);
    }

    private WebTarget client() {
        return ClientBuilder.newClient()
                .register(new JacksonJaxbJsonProvider(BITSHADOW_SERVER.getObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS))
                .target(String.format("http://localhost:%d", BITSHADOW_SERVER.getLocalPort()));
    }

}
