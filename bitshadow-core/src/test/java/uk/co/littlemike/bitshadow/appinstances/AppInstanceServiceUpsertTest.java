package uk.co.littlemike.bitshadow.appinstances;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppService;
import uk.co.littlemike.bitshadow.apps.AppUpdate;
import uk.co.littlemike.bitshadow.apps.TestApp;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostService;
import uk.co.littlemike.bitshadow.hosts.HostUpdate;
import uk.co.littlemike.bitshadow.hosts.TestHost;

import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceUpsertTest {
    private static final String ID = "Id";
    private static final String APP_NAME = "App name";
    private static final String HOSTNAME = "hostname";
    private static final App UPSERTED_APP = new TestApp().withName(APP_NAME);
    private static final Host UPSERTED_HOST = new TestHost().withHostname(HOSTNAME);

    private RegisterAppInstance update = spy(new RegisterAppInstance() {
        @Override
        public void applyTo(AppInstance appInstance) {}

        @Override
        public String getAppName() {
            return APP_NAME;
        }

        @Override
        public AppUpdate getAppUpdate() {
            return appUpdate;
        }

        @Override
        public String getHostname() {
            return HOSTNAME;
        }

        @Override
        public HostUpdate getHostUpdate() {
            return hostUpdate;
        }
    });

    @Mock
    public AppUpdate appUpdate;

    @Mock
    public HostUpdate hostUpdate;

    @Mock
    public AppInstanceRepository appInstanceRepository;

    @Mock
    public AppService appService;

    @Mock
    public HostService hostService;

    @InjectMocks
    public AppInstanceService service;

    @Before
    public void repositoryReturnsSavedInstance() {
        when(appInstanceRepository.save(any(AppInstance.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Before
    public void appServiceReturnsUpsertedApp() {
        when(appService.upsert(APP_NAME, appUpdate)).thenReturn(UPSERTED_APP);
    }

    @Before
    public void hostServiceReturnsUpsertedHost() {
        when(hostService.upsert(HOSTNAME, hostUpdate)).thenReturn(UPSERTED_HOST);
    }

    @Test
    public void appliesUpdateToAppInstanceThenSaves() {
        AppInstance instance = new TestAppInstance();
        existingInstanceIs(instance);

        service.register(ID, update);

        InOrder order = inOrder(update, appInstanceRepository);
        order.verify(update).applyTo(instance);
        order.verify(appInstanceRepository).save(instance);
    }

    @Test
    public void createsNewInstanceUpdatesAndSaves() {
        existingInstanceIs(null);

        AppInstance savedInstance = service.register(ID, update);

        assertThat(savedInstance.getId()).isEqualTo(ID);
        verify(update).applyTo(savedInstance);
        verify(appInstanceRepository).save(savedInstance);
    }

    @Test
    public void setsUpsertedAppOnNewInstance() {
        existingInstanceIs(null);

        AppInstance savedInstance = service.register(ID, update);

        assertThat(savedInstance.getApp()).isSameAs(UPSERTED_APP);
    }

    @Test
    public void setsUpsertedAppOnExistingInstance() {
        AppInstance instance = new TestAppInstance();
        existingInstanceIs(instance);

        AppInstance savedInstance = service.register(ID, update);

        assertThat(savedInstance.getApp()).isSameAs(UPSERTED_APP);
    }

    @Test
    public void setsUpsertedHostOnNewInstance() {
        existingInstanceIs(null);

        AppInstance savedInstance = service.register(ID, update);

        assertThat(savedInstance.getHost()).isSameAs(UPSERTED_HOST);
    }

    @Test
    public void setsUpsertedHostOnExistingInstance() {
        AppInstance instance = new TestAppInstance();
        existingInstanceIs(instance);

        AppInstance savedInstance = service.register(ID, update);

        assertThat(savedInstance.getHost()).isSameAs(UPSERTED_HOST);
    }

    private void existingInstanceIs(AppInstance instance) {
        when(appInstanceRepository.findById(ID)).thenReturn(Optional.ofNullable(instance));
    }
}
