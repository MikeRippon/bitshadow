package uk.co.littlemike.bitshadow.web.appinstance;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;

import javax.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AppInstanceRepresentation {

    @NotNull
    private String id;

    public AppInstanceRepresentation(AppInstance instance) {
        id = instance.getId();
    }

    public AppInstanceRepresentation() {
    }

    public AppInstanceRepresentation withId(String id) {
        this.id = id;
        return this;
    }

    public AppInstance toDomain() {
        return new AppInstance()
                .withId(id);
    }
}
