package uk.co.littlemike.bitshadow.persistence.wiring;

import com.google.inject.AbstractModule;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceRepository;
import uk.co.littlemike.bitshadow.persistence.inmemory.InMemoryAppInstanceRepository;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppInstanceRepository.class).to(InMemoryAppInstanceRepository.class);
    }
}
