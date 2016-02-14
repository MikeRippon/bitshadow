package uk.co.littlemike.bitshadow.hosts;

import java.util.List;

public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public Host getByHostname(String hostname) {
        return repository.getByHostname(hostname);
    }

    public List<Host> getAll() {
        return repository.getAll();
    }

    public Host upsert(String hostname, HostUpdate update) {
        Host host = repository.findByHostname(hostname).orElse(new Host(hostname));
        update.applyTo(host);
        repository.save(host);
        return host;
    }
}
