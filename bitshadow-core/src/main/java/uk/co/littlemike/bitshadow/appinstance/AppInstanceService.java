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

    public AppInstance registerAppInstance(AppInstance appInstance) {
        return appInstanceRepository.registerAppInstance(appInstance);
    }

    public AppInstance getById(String id) {
        return appInstanceRepository.getById(id);
    }
}
