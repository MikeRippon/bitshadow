package uk.co.littlemike.bitshadow.hosts;

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
public class HostServiceTest {
    private static final String HOSTNAME = "Hostname";

    @Mock
    public HostUpdate update;

    @Mock
    public HostRepository repository;

    @InjectMocks
    public HostService service;

    @Before
    public void repositoryReturnsSavedHost() {
        when(repository.save(any(Host.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Test
    public void returnsHost() {
        Host host = new Host(HOSTNAME);
        when(repository.getByHostname(HOSTNAME)).thenReturn(host);

        Host returnedHost = service.getByHostname(HOSTNAME);

        assertThat(returnedHost).isSameAs(host);
    }

    @Test
    public void returnsHosts() {
        List<Host> hosts = new ArrayList<>();
        when(repository.getAll()).thenReturn(hosts);

        List<Host> returnedHosts = service.getAll();

        assertThat(returnedHosts).isSameAs(hosts);
    }

    @Test
    public void upsertAppliesUpdateToAppThenSaves() {
        Host host = new Host(HOSTNAME);
        when(repository.findByHostname(HOSTNAME)).thenReturn(Optional.of(host));

        service.upsert(HOSTNAME, update);

        InOrder order = inOrder(update, repository);
        order.verify(update).applyTo(host);
        order.verify(repository).save(host);
    }

    @Test
    public void upsertCreatesNewAppUpdatesAndSaves() {
        when(repository.findByHostname(HOSTNAME)).thenReturn(Optional.empty());

        Host savedHost = service.upsert(HOSTNAME, update);

        assertThat(savedHost.getHostname()).isEqualTo(HOSTNAME);
        verify(update).applyTo(savedHost);
        verify(repository).save(savedHost);
    }
}
