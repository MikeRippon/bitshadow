package uk.co.littlemike.bitshadow.client.config;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class CommandExecutor {
    public Optional<String> execute(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        try (Scanner s = new Scanner(process.getInputStream()).useDelimiter("\\A")) {
            if (s.hasNext()) {
                String result = s.next().trim();
                if (!"".equals(result)) {
                    return Optional.of(result);
                }
            }
            return Optional.empty();
        }
    }

}
