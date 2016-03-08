package uk.co.littlemike.bitshadow.client.config;

import java.util.Map;

public class HostnameResolver {
    private final Map<String, String> env;
    private final BitshadowConfiguration config;
    private final CommandExecutor commandExecutor;

    public HostnameResolver(BitshadowConfiguration config) {
        this(System.getenv(), config, new CommandExecutor());
    }

    public HostnameResolver(Map<String, String> env, BitshadowConfiguration config, CommandExecutor commandExecutor) {
        this.env = env;
        this.config = config;
        this.commandExecutor = commandExecutor;
    }

    public String getHostname() {
        if (config.getHostname().isPresent()) {
            return config.getHostname().get();
        }
        if (env.containsKey("COMPUTERNAME")) {
            return env.get("COMPUTERNAME");
        }
        if (env.containsKey("HOSTNAME")) {
            return env.get("HOSTNAME");
        }
        try {
            return commandExecutor.execute("hostname")
                    .orElseThrow(NoHostnameFoundException::new);
        } catch (Exception e) {
            throw new NoHostnameFoundException(e);
        }
    }
}
