package uk.co.littlemike.bitshadow.persistence.hosts;

import uk.co.littlemike.bitshadow.common.NotFoundException;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostRepository;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class InMemoryHostRepository implements HostRepository {
    private final Map<String, Host> hostsByHostname = new HashMap<>();

    @Override
    public Host save(Host host) {
        return hostsByHostname.put(host.getHostname(), host);
    }

    @Override
    public Host getByHostname(String hostname) {
        return findByHostname(hostname)
                .orElseThrow(() -> new NotFoundException("No host found with hostname " + hostname));
    }

    @Override
    public List<Host> getAll() {
        return new ArrayList<>(hostsByHostname.values());
    }

    @Override
    public Optional<Host> findByHostname(String hostname) {
        return Optional.ofNullable(hostsByHostname.get(hostname));
    }
}
