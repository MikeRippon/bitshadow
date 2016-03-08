package uk.co.littlemike.bitshadow.client.config;

public interface BitshadowConfiguration {
    String getAppName();

    default String getHostname() {
        return new DefaultHostnameResolver().getHostname()
                .orElseThrow(() ->new ConfigurationException("Could not establish hostname"));
    }
}
