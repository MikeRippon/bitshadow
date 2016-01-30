package uk.co.littlemike.bitshadow.web.appinstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.littlemike.bitshadow.app.App;
import uk.co.littlemike.bitshadow.app.AppUpdate;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceUpdate;

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
