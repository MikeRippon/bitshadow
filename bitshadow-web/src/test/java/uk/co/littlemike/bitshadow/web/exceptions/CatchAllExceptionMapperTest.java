package uk.co.littlemike.bitshadow.web.exceptions;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class CatchAllExceptionMapperTest {

    private CatchAllExceptionMapper mapper = new CatchAllExceptionMapper();

    @Test
    public void mapsNotFoundExceptionToRepresentation() {
        String message = "It broke";

        Response response = mapper.toResponse(new Exception(message));

        assertThat(response.getStatus()).isEqualTo(500);
        assertThat(response.getEntity()).isInstanceOf(ExceptionRepresentation.class);
    }
}
