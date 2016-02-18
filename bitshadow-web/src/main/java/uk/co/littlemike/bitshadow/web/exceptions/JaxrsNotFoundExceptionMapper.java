package uk.co.littlemike.bitshadow.web.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JaxrsNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    private static final Logger LOG = LoggerFactory.getLogger(JaxrsNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(NotFoundException exception) {
        LOG.debug("Resource not found", exception);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ExceptionRepresentation(exception))
                .build();
    }
}
