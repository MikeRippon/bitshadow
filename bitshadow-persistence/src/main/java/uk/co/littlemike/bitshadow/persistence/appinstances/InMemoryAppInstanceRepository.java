package uk.co.littlemike.bitshadow.persistence.appinstances;

import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceRepository;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    @Override
    public List<AppInstance> getByAppName(String appName) {
        return instancesById.values().stream()
                .filter(i -> i.getApp().getName().equals(appName))
                .collect(toList());
    }
}
