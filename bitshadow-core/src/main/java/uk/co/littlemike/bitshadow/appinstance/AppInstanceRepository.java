package uk.co.littlemike.bitshadow.appinstance;

import java.util.Optional;

public interface AppInstanceRepository {
    AppInstance getById(String id);

    Optional<AppInstance> findById(String id);

    AppInstance save(AppInstance instance);
}
