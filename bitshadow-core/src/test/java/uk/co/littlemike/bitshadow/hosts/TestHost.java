package uk.co.littlemike.bitshadow.hosts;

public class TestHost extends Host {

    public TestHost() {
        super("a-hostname");
    }

    public TestHost withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }
}
