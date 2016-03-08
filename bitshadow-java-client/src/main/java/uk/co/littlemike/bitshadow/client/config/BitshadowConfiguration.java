package uk.co.littlemike.bitshadow.client.config;

import java.util.Optional;

public interface BitshadowConfiguration {
    String getAppName();

    default Optional<String> getHostname() {
        return Optional.empty();
    }
}
