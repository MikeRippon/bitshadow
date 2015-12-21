package uk.co.littlemike.bitshadow.persistence.wiring;

import com.google.inject.AbstractModule;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;
import uk.co.littlemike.bitshadow.persistence.inmemory.InMemoryServiceRepository;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServicesRepository.class).to(InMemoryServiceRepository.class);
    }
}
