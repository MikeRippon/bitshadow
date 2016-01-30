package uk.co.littlemike.bitshadow.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import uk.co.littlemike.bitshadow.web.config.BitShadowConfiguration;
import uk.co.littlemike.bitshadow.web.wiring.BitShadowWebModule;

public class BitShadowWebService extends Application<BitShadowConfiguration> {

    public static void main(String[] args) throws Exception {
        new BitShadowWebService().run("server");
    }

    @Override
    public void initialize(Bootstrap<BitShadowConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new BitShadowWebModule())
                .build()
        );
        bootstrap.addBundle(new AssetsBundle("/swagger", "/swagger", "index.html"));

        bootstrap.getObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .findAndRegisterModules();
    }

    @Override
    public void run(BitShadowConfiguration configuration, Environment environment) throws Exception {
        JerseyEnvironment jersey = environment.jersey();
        jersey.packages("uk.co.littlemike.bitshadow.web");
        jersey.register(new ApiListingResource());
        jersey.register(new SwaggerSerializers());

        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setTitle("BitShadow web service");
        swaggerConfig.setVersion("0.0.1");
        swaggerConfig.setResourcePackage("uk.co.littlemike.bitshadow.web");
        swaggerConfig.setScan(true);
        swaggerConfig.setBasePath("/");
    }
}
