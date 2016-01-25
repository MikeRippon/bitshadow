package uk.co.littlemike.bitshadow.web.exceptions;

import org.junit.Test;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundExceptionMapperTest {

    private NotFoundExceptionMapper mapper = new NotFoundExceptionMapper();

    @Test
    public void mapsNotFoundExceptionToRepresentation() {
        String message = "Not found";

        Response response = mapper.toResponse(new NotFoundException(message));

        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getEntity()).isInstanceOf(ExceptionRepresentation.class);
    }
}