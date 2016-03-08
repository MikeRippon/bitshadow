package uk.co.littlemike.bitshadow.client.config;

import java.util.Map;
import java.util.Optional;

public class DefaultHostnameResolver {
    private final Map<String, String> env;

    public DefaultHostnameResolver() {
        this(System.getenv());
    }

    public DefaultHostnameResolver(Map<String, String> env) {
        this.env = env;
    }

    public Optional<String> getHostname() {
        if (env.containsKey("COMPUTERNAME")) {
            return Optional.of(env.get("COMPUTERNAME"));
        }
        if (env.containsKey("HOSTNAME")) {
            return Optional.of(env.get("HOSTNAME"));
        }
        return Optional.empty();
    }
}
