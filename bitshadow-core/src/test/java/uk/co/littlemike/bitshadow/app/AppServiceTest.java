package uk.co.littlemike.bitshadow.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppServiceTest {
    private static final String NAME = "Name";

    @Mock
    public AppUpdate update;

    @Mock
    public AppRepository repository;

    @InjectMocks
    public AppService service;

    @Before
    public void repositoryReturnsSavedApp() {
        when(repository.save(any(App.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Test
    public void returnsApp() {
        App app = new App(NAME);
        when(repository.getByName(NAME)).thenReturn(app);

        App returnedApp = service.getByName(NAME);

        assertThat(returnedApp).isSameAs(app);
    }

    @Test
    public void returnsApps() {
        List<App> apps = new ArrayList<>();
        when(repository.getAll()).thenReturn(apps);

        List<App> returnedApps = service.getAll();

        assertThat(returnedApps).isSameAs(apps);
    }

    @Test
    public void upsertAppliesUpdateToAppThenSaves() {
        App app = new App(NAME);
        when(repository.findByName(NAME)).thenReturn(Optional.of(app));

        service.upsert(NAME, update);

        InOrder order = inOrder(update, repository);
        order.verify(update).applyTo(app);
        order.verify(repository).save(app);
    }

    @Test
    public void upsertCreatesNewAppUpdatesAndSaves() {
        when(repository.findByName(NAME)).thenReturn(Optional.empty());

        App savedApp = service.upsert(NAME, update);

        assertThat(savedApp.getName()).isEqualTo(NAME);
        verify(update).applyTo(savedApp);
        verify(repository).save(savedApp);
    }
}
