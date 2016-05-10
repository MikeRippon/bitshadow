package uk.co.littlemike.bitshadow.appinstances;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceTest {
    private static final String ID = "Id";
    private static final String APP_NAME = "App name";
    private static final String HOSTNAME = "hostname";

    @Mock
    public AppInstanceRepository appInstanceRepository;

    @Mock
    public AppInstanceUpdate update;

    @InjectMocks
    public AppInstanceService service;

    @Test
    public void returnsAppInstance() {
        AppInstance instance = new TestAppInstance().withId(ID);
        repositoryContains(instance);

        AppInstance returnedInstance = service.getById(ID);

        assertThat(returnedInstance).isSameAs(instance);
    }

    @Test
    public void returnsAppsByName() {
        ArrayList<AppInstance> instances = new ArrayList<>();
        when(appInstanceRepository.getByAppName(APP_NAME)).thenReturn(instances);

        List<AppInstance> returnedInstances = service.getByAppName(APP_NAME);

        assertThat(returnedInstances).isSameAs(instances);
    }

    @Test
    public void returnsAppsByHostname() {
        ArrayList<AppInstance> instances = new ArrayList<>();
        when(appInstanceRepository.getByHostname(HOSTNAME)).thenReturn(instances);

        List<AppInstance> returnedInstances = service.getByHostname(HOSTNAME);

        assertThat(returnedInstances).isSameAs(instances);
    }

    @Test
    public void updatesAppInstanceBeforeSaving() {
        AppInstance existingInstance = new TestAppInstance().withId(ID);
        repositoryContains(existingInstance);

        service.update(ID, update);

        InOrder inOrder = inOrder(appInstanceRepository, update);
        inOrder.verify(update).applyTo(existingInstance);
        inOrder.verify(appInstanceRepository).save(existingInstance);
    }

    @Test
    public void setsLastUpdatedTimeOnUpdate() {
        Instant previousLastUpdated = Instant.now().minusSeconds(60);
        AppInstance existingInstance = new TestAppInstance().withId(ID).withLastUpdated(previousLastUpdated);
        repositoryContains(existingInstance);

        service.update(ID, update);

        assertThat(existingInstance.getLastUpdated()).isNotEqualTo(previousLastUpdated);
    }

    @Test
    public void returnsSavedAppInstanceAfterUpdate() {
        AppInstance existingInstance = new TestAppInstance().withId(ID);
        AppInstance savedInstance = new TestAppInstance();
        repositoryContains(existingInstance);
        when(appInstanceRepository.save(existingInstance)).thenReturn(savedInstance);

        AppInstance returnedInstance = service.update(ID, update);

        assertThat(returnedInstance).isSameAs(savedInstance);
    }

    private void repositoryContains(AppInstance instance) {
        when(appInstanceRepository.getById(instance.getId())).thenReturn(instance);
        when(appInstanceRepository.findById(instance.getId())).thenReturn(Optional.of(instance));
    }
}
