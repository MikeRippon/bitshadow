package uk.co.littlemike.bitshadow.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.client.config.BitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;
import uk.co.littlemike.bitshadow.client.endpoint.BitshadowEndpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BitshadowServiceTest {
    private static final String GUID = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    private static final String APP_NAME = "App name";
    private static final String HOSTNAME = "Hostname";

    @Mock
    public BitshadowEndpoint endpoint;

    private BitshadowConfiguration config = new PojoBitshadowConfiguration(APP_NAME);
    private BitshadowService service;

    @Before
    public void setUp() {
        service = new BitshadowService(config, endpoint);
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

    private AppInstance registeredAppInstance() {
        ArgumentCaptor<AppInstance> captor = ArgumentCaptor.forClass(AppInstance.class);
        verify(endpoint).registerInstance(captor.capture());
        return captor.getValue();
    }

}
