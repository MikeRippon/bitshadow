package uk.co.littlemike.bitshadow.web.appinstances;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.littlemike.bitshadow.appinstances.AppInstance;
import uk.co.littlemike.bitshadow.appinstances.RegisterAppInstance;
import uk.co.littlemike.bitshadow.apps.App;
import uk.co.littlemike.bitshadow.apps.AppUpdate;
import uk.co.littlemike.bitshadow.hosts.Host;
import uk.co.littlemike.bitshadow.hosts.HostUpdate;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class RegisterAppInstanceRepresentation implements RegisterAppInstance, AppUpdate, HostUpdate {
    @JsonIgnore
    private Instant timeRegistered = Instant.now();
    @NotNull
    private String appName;
    private String appDescription;
    private String hostname;

    public void setTimeRegistered(Instant timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public AppUpdate getAppUpdate() {
        return this;
    }

    @Override
    public HostUpdate getHostUpdate() {
        return this;
    }

    @Override
    public void applyTo(AppInstance appInstance) {
        appInstance.setTimeRegistered(timeRegistered);
    }

    @Override
    public void applyTo(App app) {
        app.setDescription(appDescription);
    }

    @Override
    public void applyTo(Host host) {
    }
}
