package uk.co.littlemike.bitshadow.appinstances;

import java.util.List;
import java.util.Optional;

public interface AppInstanceRepository {
    AppInstance getById(String id);

    Optional<AppInstance> findById(String id);

    AppInstance save(AppInstance instance);

    List<AppInstance> getByAppName(String appName);
}
