package uk.co.littlemike.bitshadow.persistence;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
