package uk.co.littlemike.bitshadow.web.representations;

import uk.co.littlemike.bitshadow.model.Service;

public class ServiceRepresentation {
    private final Service service;

    public ServiceRepresentation(Service service) {
        this.service = service;
    }

    public String getId() {
        return service.getId();
    }
}
