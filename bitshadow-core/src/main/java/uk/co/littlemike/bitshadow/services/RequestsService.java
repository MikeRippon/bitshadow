package uk.co.littlemike.bitshadow.services;

import uk.co.littlemike.bitshadow.persistence.ServicesRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RequestsService {

    private final ServicesRepository servicesRepository;

    @Inject
    public RequestsService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public void createRequest(String serviceId) {
        servicesRepository.ensureServiceExists(serviceId);
    }
}
