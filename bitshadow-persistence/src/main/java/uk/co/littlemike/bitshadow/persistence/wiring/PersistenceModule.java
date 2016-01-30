package uk.co.littlemike.bitshadow.persistence.wiring;

import com.google.inject.AbstractModule;
import uk.co.littlemike.bitshadow.app.AppRepository;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceRepository;
import uk.co.littlemike.bitshadow.persistence.app.InMemoryAppRepository;
import uk.co.littlemike.bitshadow.persistence.appinstance.InMemoryAppInstanceRepository;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppRepository.class).to(InMemoryAppRepository.class);
        bind(AppInstanceRepository.class).to(InMemoryAppInstanceRepository.class);
    }
}
