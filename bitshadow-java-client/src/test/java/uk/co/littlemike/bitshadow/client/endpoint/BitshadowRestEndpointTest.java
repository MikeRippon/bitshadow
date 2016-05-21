package uk.co.littlemike.bitshadow.client.endpoint;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.littlemike.bitshadow.client.TestAppInstance;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BitshadowRestEndpointTest {
    private static final int PORT = 11856;
    private static final String HOST = "http://localhost:" + PORT;
    private static final String APP_INSTANCE_ID = "App-123";
    private static final String APP_NAME = "App name";
    private static final String HOSTNAME = "Hostname";

    @ClassRule
    public static WireMockClassRule wireMock = new WireMockClassRule(PORT);

    @Rule
    public WireMockClassRule wireMock2 = wireMock;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    BitshadowEndpoint endpoint;

    @Before
    public void setUp() {
        endpoint = new BitshadowRestEndpoint(HOST);
    }

    @Test
    public void registersAppInstance() {
        stubFor(put(urlMatching("/app-instances/.*")).willReturn(aResponse().withStatus(201)));

        endpoint.registerInstance(new TestAppInstance()
                .withId(APP_INSTANCE_ID)
                .withAppName(APP_NAME)
                .withHostname(HOSTNAME)
        );

        verify(putRequestedFor(urlMatching("/app-instances/" + APP_INSTANCE_ID))
                .withHeader("Content-Type", equalTo("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .withRequestBody(containing(APP_NAME))
                .withRequestBody(containing(HOSTNAME))
        );
    }

    @Test
    public void throwsExceptionIfUnableToRegisterInstance() {
        stubFor(put(urlMatching("/app-instances/.*")).willReturn(aResponse().withStatus(300)));

        exception.expect(BitshadowEndpointException.class);
        exception.expectMessage(HOST + "/app-instances");

        endpoint.registerInstance(new TestAppInstance());
    }

    @Test
    public void sendsHeartbeat() {
        stubFor(put(urlMatching("/app-instances/.*/status")).willReturn(aResponse().withStatus(200)));

        boolean result = endpoint.heartbeat(APP_INSTANCE_ID);

        assertThat(result).isTrue();
        verify(putRequestedFor(urlMatching("/app-instances/" + APP_INSTANCE_ID + "/status"))
               .withHeader("Content-Type", equalTo("application/json"))
               .withHeader("Accept", equalTo("application/json"))
        );
    }

    @Test
    public void returnsFalseIfHeartbeatFails() {
        stubFor(put(urlMatching("/app-instances/.*/status")).willReturn(aResponse().withStatus(300)));

        boolean result = endpoint.heartbeat(APP_INSTANCE_ID);

        assertThat(result).isFalse();
    }
}
