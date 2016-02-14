package uk.co.littlemike.bitshadow.web.hosts;

import org.junit.Test;
import uk.co.littlemike.bitshadow.hosts.Host;

import static org.assertj.core.api.Assertions.assertThat;

public class HostRepresentationTest {
    private static final String HOSTNAME = "Hostname";

    @Test
    public void mapsFromDomain() {
        Host host = new Host(HOSTNAME);

        HostRepresentation representation = new HostRepresentation(host);

        assertThat(representation.getHostname()).isEqualTo(HOSTNAME);
    }
}
