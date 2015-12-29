package uk.co.littlemike.bitshadow.web.representations;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionRepresentation {

    private final Exception exception;

    public ExceptionRepresentation(Exception exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return exception.getMessage();
    }

    public String getType() {
        return exception.getClass().getName();
    }

    public String[] getStackTrace() {
        return ExceptionUtils.getStackFrames(exception);
    }
}
