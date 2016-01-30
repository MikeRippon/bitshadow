package uk.co.littlemike.bitshadow.persistence.inmemory;

import uk.co.littlemike.bitshadow.appinstance.AppInstance;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceRepository;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class InMemoryAppInstanceRepository implements AppInstanceRepository {

    private final Map<String, AppInstance> instancesById = new HashMap<>();

    @Override
    public AppInstance getById(String id) {
        return findById(id)
                .orElseThrow(() -> new NotFoundException("No app instance found with id " + id));
    }

    @Override
    public Optional<AppInstance> findById(String id) {
        return Optional.ofNullable(instancesById.get(id));
    }

    @Override
    public AppInstance save(AppInstance instance) {
        instancesById.put(instance.getId(), instance);
        return instance;
    }
}
