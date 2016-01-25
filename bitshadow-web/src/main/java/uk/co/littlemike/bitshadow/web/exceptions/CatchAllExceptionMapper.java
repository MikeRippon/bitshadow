package uk.co.littlemike.bitshadow.web.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response
                .serverError()
                .entity(new ExceptionRepresentation(exception))
                .build();
    }
}
