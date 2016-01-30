package uk.co.littlemike.bitshadow.appinstance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceTest {
    private static final String ID = "Id";

    @Mock
    public AppInstanceUpdate update;

    @Mock
    public AppInstanceRepository appInstanceRepository;

    @InjectMocks
    public AppInstanceService service;

    @Before
    public void repositoryReturnsSavedInstance() {
        when(appInstanceRepository.save(any(AppInstance.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Test
    public void returnsAppInstance() {
        AppInstance instance = new AppInstance(ID);
        when(appInstanceRepository.getById(ID)).thenReturn(instance);

        AppInstance returnedInstance = service.getById(ID);

        assertThat(returnedInstance).isSameAs(instance);
    }

    @Test
    public void appliesUpdateToAppInstanceThenSaves() {
        AppInstance instance = new AppInstance(ID);
        when(appInstanceRepository.findById(ID)).thenReturn(Optional.of(instance));

        service.upsert(ID, update);

        InOrder order = inOrder(update, appInstanceRepository);
        order.verify(update).applyTo(instance);
        order.verify(appInstanceRepository).save(instance);
    }

    @Test
    public void createsNewInstanceUpdatesAndSaves() {
        when(appInstanceRepository.findById(ID)).thenReturn(Optional.empty());

        AppInstance savedInstance = service.upsert(ID, update);

        assertThat(savedInstance.getId()).isEqualTo(ID);
        verify(update).applyTo(savedInstance);
        verify(appInstanceRepository).save(savedInstance);
    }
}
