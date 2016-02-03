package uk.co.littlemike.bitshadow.appinstances;

import uk.co.littlemike.bitshadow.apps.AppUpdate;

public interface AppInstanceUpdate {
    void applyTo(AppInstance appInstance);

    AppUpdate getAppUpdate();

    String getAppName();
}
