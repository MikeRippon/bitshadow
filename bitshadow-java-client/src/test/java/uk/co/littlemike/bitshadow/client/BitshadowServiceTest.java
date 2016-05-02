package uk.co.littlemike.bitshadow.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.HostnameResolver;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpointException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    BitshadowConfiguration config = new PojoBitshadowConfiguration(APP_NAME, "localhost:8080");

    BitshadowService service;

    @Before
    public void setUp() {
        when(hostnameResolver.getHostname()).thenReturn(HOSTNAME);
        service = new BitshadowService(config, endpoint, hostnameResolver);
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
        doThrow(new BitshadowEndpointException("Oh no")).when(endpoint).registerInstance(any(AppInstance.class));

        service.start();

        assertThat(service.getRegistrationStatus()).isEqualTo(FAILED);
    }

    private AppInstance registeredAppInstance() {
        ArgumentCaptor<AppInstance> captor = ArgumentCaptor.forClass(AppInstance.class);
        verify(endpoint).registerInstance(captor.capture());
        return captor.getValue();
    }

}
