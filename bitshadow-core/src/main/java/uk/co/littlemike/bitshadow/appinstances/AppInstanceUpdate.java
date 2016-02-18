package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.AppUpdate;
import uk.co.littlemike.bitshadow.hosts.HostUpdate;

public interface AppInstanceUpdate {
    void applyTo(AppInstance appInstance);

    String getAppName();

    AppUpdate getAppUpdate();

    String getHostname();

    HostUpdate getHostUpdate();
}
