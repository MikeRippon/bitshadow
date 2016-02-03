package uk.co.littlemike.bitshadow.web.appinstances;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.AppInstanceUpdate;
import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppUpdate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RegisterAppInstanceRepresentation implements AppInstanceUpdate, AppUpdate {
    @JsonIgnore
    private LocalDateTime timeRegistered = LocalDateTime.now();
    @NotNull
    private String appName;
    private String appDescription;

    @Override
    public AppUpdate getAppUpdate() {
        return this;
    }

    @Override
    public void applyTo(AppInstance appInstance) {
        appInstance.setTimeRegistered(timeRegistered);
    }

    public void setTimeRegistered(LocalDateTime timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public void applyTo(App app) {
        app.setDescription(appDescription);
    }
}
