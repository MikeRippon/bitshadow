package uk.co.littlemike.bitshadow;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {

    @Test
    public void greetingShouldBeHelloWorld() {
        HelloWorld greeter = new HelloWorld();
        String greeting = greeter.getGreeting();
        assertThat(greeting)
                .isEqualTo("Hello world!");
    }
}
