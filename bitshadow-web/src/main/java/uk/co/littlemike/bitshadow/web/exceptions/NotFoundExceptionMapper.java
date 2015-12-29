package uk.co.littlemike.bitshadow.web.exceptions;

import uk.co.littlemike.bitshadow.persistence.NotFoundException;
import uk.co.littlemike.bitshadow.web.representations.ExceptionRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ExceptionRepresentation(exception))
                .build();
    }
}
