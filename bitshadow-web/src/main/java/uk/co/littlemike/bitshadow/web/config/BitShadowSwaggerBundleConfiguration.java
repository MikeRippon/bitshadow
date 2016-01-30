package uk.co.littlemike.bitshadow.web.config;

import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class BitShadowSwaggerBundleConfiguration extends SwaggerBundleConfiguration{

    public BitShadowSwaggerBundleConfiguration() {
        setResourcePackage("uk.co.littlemike.bitshadow.web");
    }
}
