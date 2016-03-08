package uk.co.littlemike.bitshadow.client.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HostnameResolverTest {
    private static final String HOSTNAME = "my-computer";

    Map<String, String> environmentVariables = new HashMap<>();
    @Mock BitshadowConfiguration config;
    @Mock CommandExecutor commandExecutor;

    HostnameResolver resolver;

    @Before
    public void setUp () throws IOException {
        when(config.getHostname()).thenReturn(Optional.empty());
        when(commandExecutor.execute(any(String.class))).thenReturn(Optional.empty());
    }

    @Test
    public void usesConfiguredHostnameIfAvailable() {
        when(config.getHostname()).thenReturn(Optional.of(HOSTNAME));
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        assertThat(resolver.getHostname()).isEqualTo(HOSTNAME);
    }

    @Test
    public void usesComputerNameVariableIfAvailable() {
        environmentVariables.put("COMPUTERNAME", HOSTNAME);
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        assertThat(resolver.getHostname()).isEqualTo(HOSTNAME);
    }

    @Test
    public void usesHostnameVariableIfAvailable() {
        environmentVariables.put("HOSTNAME", HOSTNAME);
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        assertThat(resolver.getHostname()).isEqualTo(HOSTNAME);
    }

    @Test
    public void returnsHostnameFromHostnameCommandIfNoEnvironmentVariablesSet() throws IOException {
        when(commandExecutor.execute("hostname")).thenReturn(Optional.of(HOSTNAME));
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        assertThat(resolver.getHostname()).isEqualTo(HOSTNAME);
    }

    @Test(expected = NoHostnameFoundException.class)
    public void throwsExceptionIfNoHostnameResolved() {
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        resolver.getHostname();
    }

    @Test(expected = NoHostnameFoundException.class)
    public void throwsNoHostnameFoundExceptionIfCommandExecutorFails() throws IOException {
        when(commandExecutor.execute("hostname")).thenThrow(new RuntimeException("Oops"));
        resolver = new HostnameResolver(environmentVariables, config, commandExecutor);

        resolver.getHostname();
    }
}
