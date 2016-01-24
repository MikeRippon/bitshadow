package uk.co.littlemike.bitshadow.web.config;

import io.dropwizard.server.DefaultServerFactory;

public class BitShadowServerFactory extends DefaultServerFactory {

    public BitShadowServerFactory() {
        setRegisterDefaultExceptionMappers(false);
    }
}
