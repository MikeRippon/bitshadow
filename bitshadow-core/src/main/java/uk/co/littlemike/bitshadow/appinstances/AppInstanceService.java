package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppService;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppInstanceService {

    private final AppInstanceRepository appInstanceRepository;
    private final AppService appService;
    private final HostService hostService;

    @Inject
    public AppInstanceService(
            AppInstanceRepository appInstanceRepository,
            AppService appService,
            HostService hostService) {
        this.appInstanceRepository = appInstanceRepository;
        this.appService = appService;
        this.hostService = hostService;
    }

    public AppInstance getById(String id) {
        return appInstanceRepository.getById(id);
    }

    public AppInstance upsert(String id, AppInstanceUpdate update) {
        App app = appService.upsert(update.getAppName(), update.getAppUpdate());
        Host host = hostService.upsert(update.getHostname(), update.getHostUpdate());
        AppInstance instance = appInstanceRepository.findById(id)
                .orElse(new AppInstance(id, app, host));
        update.applyTo(instance);
        instance.setApp(app);
        instance.setHost(host);
        return appInstanceRepository.save(instance);
    }
}
