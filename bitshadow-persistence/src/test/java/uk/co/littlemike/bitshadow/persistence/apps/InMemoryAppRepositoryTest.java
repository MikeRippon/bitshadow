package uk.co.littlemike.bitshadow.persistence.apps;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppRepository;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAppRepositoryTest {
    private static final String NAME = "Name";

    private AppRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryAppRepository();
    }

    @Test(expected = NotFoundException.class)
    public void getThrowsExceptionIfAppDoesNotExist() {
        repository.getByName(NAME);
    }

    @Test
    public void findReturnsEmptyIfAppDoesNotExist() {
        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @Test
    public void canFindAndGetSavedApp() {
        App savedApp = new App(NAME);

        repository.save(savedApp);

        assertThat(repository.getByName(NAME)).isSameAs(savedApp);
        assertThat(repository.findByName(NAME)).contains(savedApp);
    }

    @Test
    public void canGetAllSavedApps() {
        App app1 = new App("App 1");
        App app2 = new App("App 2");
        repository.save(app1);
        repository.save(app2);

        List<App> apps = repository.getAll();

        assertThat(apps).contains(app1, app2);
    }
}
