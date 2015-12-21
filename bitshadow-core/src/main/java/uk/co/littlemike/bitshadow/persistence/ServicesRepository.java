package uk.co.littlemike.bitshadow.persistence;

import uk.co.littlemike.bitshadow.model.Service;

public interface ServicesRepository {

    Service getById(String id);

    void ensureServiceExists(String serviceId);
}
