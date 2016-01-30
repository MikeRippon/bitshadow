package uk.co.littlemike.bitshadow.web.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class BitShadowConfiguration extends Configuration {

    @JsonProperty("swagger")
    public BitShadowSwaggerBundleConfiguration swaggerBundleConfiguration
            = new BitShadowSwaggerBundleConfiguration();

    public BitShadowConfiguration() {
        setServerFactory(new BitShadowServerFactory());
    }
}
