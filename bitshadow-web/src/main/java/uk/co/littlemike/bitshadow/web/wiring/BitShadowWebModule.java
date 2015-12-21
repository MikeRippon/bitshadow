package uk.co.littlemike.bitshadow.web.wiring;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;
import uk.co.littlemike.bitshadow.persistence.wiring.PersistenceModule;

public class BitShadowWebModule extends DropwizardAwareModule<Configuration> {
    @Override
    protected void configure() {
        bind(Configuration.class).toInstance(configuration());
        bind(Environment.class).toInstance(environment());
        bind(Bootstrap.class).toInstance(bootstrap());

        install(new PersistenceModule());
    }
}
