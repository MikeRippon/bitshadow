package uk.co.littlemike.bitshadow.web.representations;

import javax.validation.constraints.NotNull;

public class InboundRequestRepresentation {

    @NotNull
    private String serviceId;

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public InboundRequestRepresentation withServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getServiceId() {
        return serviceId;
    }
}
