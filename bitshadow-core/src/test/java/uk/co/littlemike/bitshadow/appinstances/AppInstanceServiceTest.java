package uk.co.littlemike.bitshadow.appinstances;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceTest {
    private static final String ID = "Id";
    private static final String APP_NAME = "App name";

    @Mock
    public AppInstanceRepository appInstanceRepository;

    @InjectMocks
    public AppInstanceService service;

    @Test
    public void returnsAppInstance() {
        AppInstance instance = new TestAppInstance().withId(ID);
        when(appInstanceRepository.getById(ID)).thenReturn(instance);

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
}
