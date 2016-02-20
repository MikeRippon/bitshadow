package uk.co.littlemike.bitshadow.persistence.appinstances;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.TestAppInstance;
import uk.co.littlemike.bitshadow.apps.TestApp;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAppInstanceRepositoryTest {
    private static final String ID = "Id";

    private InMemoryAppInstanceRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryAppInstanceRepository();
    }

    @Test(expected = NotFoundException.class)
    public void getThrowsExceptionIfInstanceDoesNotExist() {
        repository.getById(ID);
    }

    @Test
    public void findReturnsEmptyIfInstanceDoesNotExist() {
        assertThat(repository.findById(ID)).isEmpty();
    }

    @Test
    public void canFindAndGetSavedInstance() {
        AppInstance savedInstance = new TestAppInstance().withId(ID);

        repository.save(savedInstance);

        assertThat(repository.getById(ID)).isSameAs(savedInstance);
        assertThat(repository.findById(ID)).contains(savedInstance);
    }

    @Test
    public void canGetInstancesByAppName() {
        String appName = "App name";
        AppInstance instance1 = new TestAppInstance().withApp(new TestApp().withName(appName));
        AppInstance instance2 = new TestAppInstance().withApp(new TestApp().withName(appName));
        AppInstance instance3 = new TestAppInstance().withApp(new TestApp().withName("Another app name"));

        repository.save(instance1);
        repository.save(instance2);
        repository.save(instance3);

        List<AppInstance> instances = repository.getByAppName(appName);

        assertThat(instances).contains(instance1, instance2);
    }
}
