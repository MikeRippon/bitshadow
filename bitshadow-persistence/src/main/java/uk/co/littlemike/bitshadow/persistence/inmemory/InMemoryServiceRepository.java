package uk.co.littlemike.bitshadow.persistence.inmemory;

import uk.co.littlemike.bitshadow.model.Service;
import uk.co.littlemike.bitshadow.persistence.NotFoundException;
import uk.co.littlemike.bitshadow.persistence.ServicesRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class InMemoryServiceRepository implements ServicesRepository {
    private final Map<String, Service> servicesById = new HashMap<>();

    @Override
    public Service getById(String id) {
        Service service = servicesById.get(id);
        if (service == null) {
            throw new NotFoundException("No service found with id " + id);
        }
        return service;
    }

    @Override
    public void ensureServiceExists(String serviceId) {
        servicesById.put(serviceId, new Service().withId(serviceId));
    }
}
