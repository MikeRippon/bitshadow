package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppService;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;

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

    public AppInstance register(String id, RegisterAppInstance registerAppInstance) {
        App app = appService.upsert(registerAppInstance.getAppName(), registerAppInstance.getAppUpdate());
        Host host = hostService.upsert(registerAppInstance.getHostname(), registerAppInstance.getHostUpdate());
        AppInstance instance = appInstanceRepository.findById(id)
                .orElse(new AppInstance(id, app, host));
        registerAppInstance.applyTo(instance);
        instance.setApp(app);
        instance.setHost(host);
        return appInstanceRepository.save(instance);
    }

    public List<AppInstance> getByAppName(String appName) {
        return appInstanceRepository.getByAppName(appName);
    }

    public List<AppInstance> getByHostname(String hostname) {
        return appInstanceRepository.getByHostname(hostname);
    }

    public AppInstance update(String id, AppInstanceUpdate update) {
        AppInstance instance = appInstanceRepository.getById(id);
        update.applyTo(instance);
        instance.setLastUpdated(Instant.now());
        return appInstanceRepository.save(instance);
    }
}
