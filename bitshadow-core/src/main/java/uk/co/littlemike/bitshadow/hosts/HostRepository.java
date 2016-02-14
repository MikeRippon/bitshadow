package uk.co.littlemike.bitshadow.hosts;

import java.util.List;
import java.util.Optional;

public interface HostRepository {
    Host save(Host host);

    Host getByHostname(String hostname);

    List<Host> getAll();

    Optional<Host> findByHostname(String hostname);
}
