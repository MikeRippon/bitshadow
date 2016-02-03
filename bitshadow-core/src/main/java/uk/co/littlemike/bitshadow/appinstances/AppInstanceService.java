package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppInstanceService {

    private final AppInstanceRepository appInstanceRepository;
    private final AppService appService;

    @Inject
    public AppInstanceService(AppInstanceRepository appInstanceRepository, AppService appService) {
        this.appInstanceRepository = appInstanceRepository;
        this.appService = appService;
    }

    public AppInstance getById(String id) {
        return appInstanceRepository.getById(id);
    }

    public AppInstance upsert(String id, AppInstanceUpdate update) {
        App app = appService.upsert(update.getAppName(), update.getAppUpdate());
        AppInstance instance = appInstanceRepository.findById(id)
                .orElse(new AppInstance(id, app));
        update.applyTo(instance);
        instance.setApp(app);
        return appInstanceRepository.save(instance);
    }
}
