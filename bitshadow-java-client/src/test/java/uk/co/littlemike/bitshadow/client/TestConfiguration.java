package uk.co.littlemike.bitshadow.client;

import uk.co.littlemike.bitshadow.client.config.PojoBitshadowConfiguration;

import static uk.co.littlemike.bitshadow.client.TestObject.uuid;

public class TestConfiguration extends PojoBitshadowConfiguration implements TestObject {
    public TestConfiguration() {
        super("App-" + uuid(), "Host-" + uuid());
    }
}
