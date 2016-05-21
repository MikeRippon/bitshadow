package uk.co.littlemike.bitshadow.client;

import java.util.UUID;

public interface TestObject {
    static String uuid() {
        return UUID.randomUUID().toString();
    }
}
