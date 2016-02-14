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

import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceUpsertTest {
    private static final String ID = "Id";
    private static final String APP_NAME = "App name";

    private AppInstanceUpdate update = spy(new AppInstanceUpdate() {
        @Override
        public void applyTo(AppInstance appInstance) {}

        @Override
        public AppUpdate getAppUpdate() {
            return appUpdate;
        }

        @Override
        public String getAppName() {
            return APP_NAME;
        }
    });

    @Mock
    public App upsertedApp;

    @Mock
    public AppUpdate appUpdate;

    @Mock
    public AppInstanceRepository appInstanceRepository;

    @Mock
    public AppService appService;

    @InjectMocks
    public AppInstanceService service;

    @Before
    public void repositoryReturnsSavedInstance() {
        when(appInstanceRepository.save(any(AppInstance.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Before
    public void appServiceReturnsUpsertedApp() {
        when(appService.upsert(APP_NAME, appUpdate)).thenReturn(upsertedApp);
    }

    @Test
    public void appliesUpdateToAppInstanceThenSaves() {
        AppInstance instance = new TestAppInstance();
        existingInstanceIs(instance);

        service.upsert(ID, update);

        InOrder order = inOrder(update, appInstanceRepository);
        order.verify(update).applyTo(instance);
        order.verify(appInstanceRepository).save(instance);
    }

    @Test
    public void createsNewInstanceUpdatesAndSaves() {
        existingInstanceIs(null);

        AppInstance savedInstance = service.upsert(ID, update);

        assertThat(savedInstance.getId()).isEqualTo(ID);
        verify(update).applyTo(savedInstance);
        verify(appInstanceRepository).save(savedInstance);
    }

    @Test
    public void setsUpsertedAppOnNewInstance() {
        existingInstanceIs(null);

        AppInstance savedInstance = service.upsert(ID, update);

        assertThat(savedInstance.getApp()).isSameAs(upsertedApp);
    }

    @Test
    public void setsUpsertedAppOnExistingInstance() {
        AppInstance instance = new TestAppInstance();
        existingInstanceIs(instance);

        AppInstance savedInstance = service.upsert(ID, update);

        assertThat(savedInstance.getApp()).isSameAs(upsertedApp);
    }

    private void existingInstanceIs(AppInstance instance) {
        when(appInstanceRepository.findById(ID)).thenReturn(Optional.ofNullable(instance));
    }
}
