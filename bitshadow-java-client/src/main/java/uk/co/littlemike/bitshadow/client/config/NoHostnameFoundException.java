package uk.co.littlemike.bitshadow.client.config;

public class NoHostnameFoundException extends RuntimeException {
    public NoHostnameFoundException(Exception e) {
        super(e);
    }

    public NoHostnameFoundException() {
    }
}
