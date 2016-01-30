package uk.co.littlemike.bitshadow.appinstance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.littlemike.bitshadow.app.AppService;
import uk.co.littlemike.bitshadow.app.AppUpdate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppInstanceServiceTest {
    private static final String ID = "Id";

    @Mock
    public AppUpdate appUpdate;

    @Mock
    public AppInstanceUpdate update;

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
    public void updateIncludesAppUpdate() {
        when(update.getAppUpdate()).thenReturn(appUpdate);
    }

    @Test
    public void returnsAppInstance() {
        AppInstance instance = new TestAppInstance().withId(ID);
        when(appInstanceRepository.getById(ID)).thenReturn(instance);

        AppInstance returnedInstance = service.getById(ID);

        assertThat(returnedInstance).isSameAs(instance);
    }
}
