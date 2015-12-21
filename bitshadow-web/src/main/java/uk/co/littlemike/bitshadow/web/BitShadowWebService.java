package uk.co.littlemike.bitshadow.web;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import uk.co.littlemike.bitshadow.web.wiring.BitShadowWebModule;

public class BitShadowWebService extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new BitShadowWebService().run("server");
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new BitShadowWebModule())
                .build()
        );
        bootstrap.addBundle(new AssetsBundle("/swagger", "/swagger", "index.html"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        JerseyEnvironment jersey = environment.jersey();
        jersey.packages("uk.co.littlemike.bitshadow.web");
        jersey.register(new ApiListingResource());
        jersey.register(new SwaggerSerializers());

        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setTitle("BitShadow web service");
        swaggerConfig.setVersion("0.0.1");
        swaggerConfig.setResourcePackage("uk.co.littlemike.bitshadow.web.resources");
        swaggerConfig.setScan(true);
        swaggerConfig.setBasePath("/");
    }
}
