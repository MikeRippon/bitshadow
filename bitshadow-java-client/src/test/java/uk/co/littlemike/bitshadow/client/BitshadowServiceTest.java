package uk.co.littlemike.bitshadow.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpointException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static uk.co.littlemike.bitshadow.client.RegistrationStatus.FAILED;
import static uk.co.littlemike.bitshadow.client.RegistrationStatus.PENDING;
import static uk.co.littlemike.bitshadow.client.RegistrationStatus.REGISTERED;

@RunWith(MockitoJUnitRunner.class)
public class BitshadowServiceTest {
    private static final String GUID = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    private static final String APP_NAME = "App name";
    private static final String HOSTNAME = "Hostname";

    @Mock BitshadowEndpoint endpoint;
    @Mock HostnameResolver hostnameResolver;
    @Mock Heartbeat heartbeat;
    BitshadowConfiguration config = new PojoBitshadowConfiguration(APP_NAME, "localhost:8080");

    BitshadowService service;

    @Before
    public void setUp() {
        when(hostnameResolver.getHostname()).thenReturn(HOSTNAME);
        service = new BitshadowService(config, endpoint, hostnameResolver, heartbeat);
    }

    @Test
    public void registersAppWhenServiceIsCreated() {
        service.start();

        AppInstance registeredInstance = registeredAppInstance();
        assertThat(registeredInstance).isNotNull();
        assertThat(registeredInstance.getId()).matches(GUID);
        assertThat(registeredInstance.getAppName()).isEqualTo(APP_NAME);
        assertThat(registeredInstance.getHostname()).isEqualTo(HOSTNAME);
    }

    @Test
    public void appRegistrationStatusIsInitiallyPending() {
        assertThat(service.getRegistrationStatus()).isEqualTo(PENDING);
    }

    @Test
    public void appRegistrationStatusIsRegisteredAfterSuccessfulRegistration() {
        service.start();

        assertThat(service.getRegistrationStatus()).isEqualTo(REGISTERED);
    }

    @Test
    public void appRegistrationStatusIsFailedWhenUnableToRegister() {
        registrationFails();

        service.start();

        assertThat(service.getRegistrationStatus()).isEqualTo(FAILED);
    }

    @Test
    public void startsHeartbeatAfterRegistration() {
        service.start();

        InOrder inOrder = inOrder(endpoint, heartbeat);
        inOrder.verify(endpoint).registerInstance(any());
        inOrder.verify(heartbeat).start(anyString());
    }

    @Test
    public void doesNotStartHeartbeatIfRegistrationFailed() {
        registrationFails();

        service.start();

        verify(heartbeat, never()).start(anyString());
    }

    @Test
    public void startsHeartbeatWithRegisteredAppInstanceId() {
        service.start();

        String instanceId = registeredAppInstance().getId();
        verify(heartbeat).start(instanceId);
    }

    @Test
    public void returnsHeartbeatStatus() {
        when(heartbeat.getStatus()).thenReturn(HeartbeatStatus.PENDING);

        HeartbeatStatus heartbeatStatus = service.getHeartbeatStatus();

        assertThat(heartbeatStatus).isEqualTo(HeartbeatStatus.PENDING);
    }

    @Test
    public void stopsHeartbeat() {
        service.start();

        service.stop();

        verify(heartbeat).stop();
    }

    private void registrationFails() {
        doThrow(new BitshadowEndpointException("Oh no")).when(endpoint).registerInstance(any(AppInstance.class));
    }

    private AppInstance registeredAppInstance() {
        ArgumentCaptor<AppInstance> captor = ArgumentCaptor.forClass(AppInstance.class);
        verify(endpoint).registerInstance(captor.capture());
        return captor.getValue();
    }

}
