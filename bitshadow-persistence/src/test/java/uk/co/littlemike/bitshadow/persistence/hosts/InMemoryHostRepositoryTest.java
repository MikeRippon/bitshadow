package uk.co.littlemike.bitshadow.persistence.hosts;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppRepository;
import uk.co.littlemike.bitshadow.common.NotFoundException;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostRepository;
import uk.co.littlemike.bitshadow.persistence.apps.InMemoryAppRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryHostRepositoryTest {
    private static final String HOSTNAME = "Hostname";

    private HostRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryHostRepository();
    }

    @Test(expected = NotFoundException.class)
    public void getThrowsExceptionIfAppDoesNotExist() {
        repository.getByHostname(HOSTNAME);
    }

    @Test
    public void findReturnsEmptyIfAppDoesNotExist() {
        assertThat(repository.findByHostname(HOSTNAME)).isEmpty();
    }

    @Test
    public void canFindAndGetSavedApp() {
        Host savedHost = new Host(HOSTNAME);

        repository.save(savedHost);

        assertThat(repository.getByHostname(HOSTNAME)).isSameAs(savedHost);
        assertThat(repository.findByHostname(HOSTNAME)).contains(savedHost);
    }

    @Test
    public void canGetAllSavedApps() {
        Host host1 = new Host("Host 1");
        Host host2 = new Host("Host 2");
        repository.save(host1);
        repository.save(host2);

        List<Host> hosts = repository.getAll();

        assertThat(hosts).contains(host1, host2);
    }
}
