package uk.co.littlemike.bitshadow.apps;

import java.util.List;
import java.util.Optional;

public interface AppRepository {
    App save(App app);

    App getByName(String name);

    Optional<App> findByName(String name);

    List<App> getAll();
}
