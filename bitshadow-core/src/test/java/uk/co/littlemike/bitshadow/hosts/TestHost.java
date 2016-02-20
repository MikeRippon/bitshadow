package uk.co.littlemike.bitshadow.hosts;

import java.util.UUID;

public class TestHost extends Host {

    public TestHost() {
        super("Host " + UUID.randomUUID().toString());
    }

    public TestHost withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }
}
