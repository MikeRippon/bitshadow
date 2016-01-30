package uk.co.littlemike.bitshadow.app;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AppService {

    private final AppRepository repository;

    @Inject
    public AppService(AppRepository repository) {
        this.repository = repository;
    }

    public App getByName(String name) {
        return repository.getByName(name);
    }

    public App upsert(String name, AppUpdate update) {
        App app = repository.findByName(name).orElse(new App(name));
        update.applyTo(app);
        repository.save(app);
        return app;
    }

    public List<App> getAll() {
        return repository.getAll();
    }
}
