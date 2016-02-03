package uk.co.littlemike.bitshadow.persistence.appinstances;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.TestAppInstance;
import uk.co.littlemike.bitshadow.common.NotFoundException;

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
}
