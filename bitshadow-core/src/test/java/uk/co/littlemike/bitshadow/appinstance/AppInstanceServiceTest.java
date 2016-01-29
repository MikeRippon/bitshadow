package uk.co.littlemike.bitshadow.appinstance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceTest {

    public static final String ID = "Id";
    @Mock
    public AppInstanceRepository appInstanceRepository;

    @InjectMocks
    public AppInstanceService service;

    @Test
    public void createsAppInstance() {
        AppInstance instance = new AppInstance(ID);
        AppInstance persistedInstance = new AppInstance(ID);
        when(appInstanceRepository.registerAppInstance(instance)).thenReturn(persistedInstance);

        AppInstance returnedInstance = service.registerAppInstance(instance);

        assertThat(returnedInstance).isSameAs(persistedInstance);
    }

    @Test
    public void returnsAppInstance() {
        AppInstance instance = new AppInstance(ID);
        when(appInstanceRepository.getById(ID)).thenReturn(instance);

        AppInstance returnedInstance = service.getById(ID);

        assertThat(returnedInstance).isSameAs(instance);
    }
}
