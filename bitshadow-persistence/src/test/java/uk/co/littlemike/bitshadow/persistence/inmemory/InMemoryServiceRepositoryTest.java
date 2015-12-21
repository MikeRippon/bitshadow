package uk.co.littlemike.bitshadow.persistence.inmemory;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.model.Service;
import uk.co.littlemike.bitshadow.persistence.NotFoundException;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryServiceRepositoryTest {
    private static final String ID = "123";

    private ServicesRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryServiceRepository();
    }

    @Test(expected = NotFoundException.class)
    public void thorwsExceptionIfServiceDoesNotExist() {
        repository.getById(ID);
    }

    @Test
    public void returnsServiceIfExists() {
        repository.ensureServiceExists(ID);

        Service service = repository.getById(ID);

        assertThat(service.getId()).isEqualTo(ID);
    }
}
