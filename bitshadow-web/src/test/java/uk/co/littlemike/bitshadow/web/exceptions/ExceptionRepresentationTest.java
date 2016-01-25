package uk.co.littlemike.bitshadow.web.exceptions;

import org.junit.Test;
import uk.co.littlemike.bitshadow.web.exceptions.ExceptionRepresentation;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionRepresentationTest {

    private static final String MESSAGE = "A message";

    @Test
    public void includesTypeMessageAndStackTrace() {
        Exception exception = new Exception(MESSAGE);

        ExceptionRepresentation representation = new ExceptionRepresentation(exception);

        assertThat(representation.getMessage()).isEqualTo(MESSAGE);
        assertThat(representation.getType()).isEqualTo("java.lang.Exception");
        assertThat(representation.getStackTrace()).isNotEmpty();
    }

}