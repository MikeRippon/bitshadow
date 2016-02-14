package uk.co.littlemike.bitshadow.web.hosts;

import uk.co.littlemike.bitshadow.hosts.Host;

public class HostRepresentation {
    private final String hostname;

    public HostRepresentation(Host host) {
        hostname = host.getHostname();
    }

    public String getHostname() {
        return hostname;
    }
}
