package uk.co.littlemike.bitshadow.persistence.inmemory;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;
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
    public void throwsExceptionIfInstanceDoesNotExist() {
        repository.getById(ID);
    }

    @Test
    public void returnsRegisteredInstance() {
        AppInstance savedInstance = new AppInstance(ID);
        repository.registerAppInstance(savedInstance);

        AppInstance returnedInstance = repository.getById(ID);

        assertThat(returnedInstance).isSameAs(savedInstance);
    }
}
