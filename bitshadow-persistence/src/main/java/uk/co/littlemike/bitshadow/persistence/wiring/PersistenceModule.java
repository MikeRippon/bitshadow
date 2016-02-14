package uk.co.littlemike.bitshadow.persistence.wiring;

import com.google.inject.AbstractModule;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceRepository;
import uk.co.littlemike.bitshadow.apps.AppRepository;
import uk.co.littlemike.bitshadow.hosts.HostRepository;
import uk.co.littlemike.bitshadow.persistence.appinstances.InMemoryAppInstanceRepository;
import uk.co.littlemike.bitshadow.persistence.apps.InMemoryAppRepository;
import uk.co.littlemike.bitshadow.persistence.hosts.InMemoryHostRepository;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppRepository.class).to(InMemoryAppRepository.class);
        bind(AppInstanceRepository.class).to(InMemoryAppInstanceRepository.class);
        bind(HostRepository.class).to(InMemoryHostRepository.class);
    }
}
