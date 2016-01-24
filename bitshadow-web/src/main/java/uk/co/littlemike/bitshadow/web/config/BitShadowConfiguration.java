package uk.co.littlemike.bitshadow.web.config;

import io.dropwizard.Configuration;

public class BitShadowConfiguration extends Configuration {

    public BitShadowConfiguration() {
        setServerFactory(new BitShadowServerFactory());
    }
}
