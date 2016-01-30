package uk.co.littlemike.bitshadow.appinstance;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppInstanceService {

    private final AppInstanceRepository appInstanceRepository;

    @Inject
    public AppInstanceService(AppInstanceRepository appInstanceRepository) {
        this.appInstanceRepository = appInstanceRepository;
    }

    public AppInstance getById(String id) {
        return appInstanceRepository.getById(id);
    }

    public AppInstance upsert(String id, AppInstanceUpdate update) {
        AppInstance instance = appInstanceRepository.findById(id).orElse(new AppInstance(id));
        update.applyTo(instance);
        return appInstanceRepository.save(instance);
    }
}
