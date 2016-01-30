package uk.co.littlemike.bitshadow.persistence.app;

import uk.co.littlemike.bitshadow.app.App;
import uk.co.littlemike.bitshadow.app.AppRepository;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class InMemoryAppRepository implements AppRepository {

    private final Map<String, App> appsByName = new HashMap<>();

    @Override
    public App save(App app) {
        return appsByName.put(app.getName(), app);
    }

    @Override
    public App getByName(String name) {
        return findByName(name)
                .orElseThrow(() -> new NotFoundException("No app found with name " + name));
    }

    @Override
    public Optional<App> findByName(String name) {
        return Optional.ofNullable(appsByName.get(name));
    }

    @Override
    public List<App> getAll() {
        return new ArrayList<>(appsByName.values());
    }
}
