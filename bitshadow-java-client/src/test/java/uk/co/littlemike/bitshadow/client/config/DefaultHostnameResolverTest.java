package uk.co.littlemike.bitshadow.client.config;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultHostnameResolverTest {
    private static final String HOSTNAME = "my-computer";

    private Map<String, String> environmentVariables = new HashMap<>();

    public DefaultHostnameResolver resolver;

    @Test
    public void returnsNullHostnameIfNoneFound() {
        resolver = new DefaultHostnameResolver(environmentVariables);

        assertThat(resolver.getHostname()).isEmpty();
    }

    @Test
    public void usesComputerNameVariableIfAvailable() {
        environmentVariables.put("COMPUTERNAME", HOSTNAME);
        resolver = new DefaultHostnameResolver(environmentVariables);

        assertThat(resolver.getHostname()).contains(HOSTNAME);
    }

    @Test
    public void usesHostnameVariableIfAvailable() {
        environmentVariables.put("HOSTNAME", HOSTNAME);
        resolver = new DefaultHostnameResolver(environmentVariables);

        assertThat(resolver.getHostname()).contains(HOSTNAME);
    }

    @Test
    public void tryIt() throws IOException {
        try {
            Process proc = Runtime.getRuntime().exec("echo test-hostname");
            try (InputStream stream = proc.getInputStream()) {
                try (Scanner s = new Scanner(stream).useDelimiter("\\A")) {
                    System.out.println(s.hasNext() ? s.next().trim() : "");
                }
            }
        } catch (IOException e) {
            System.out.println("That didn't work!");
        }
    }
}
