package uk.co.littlemike.bitshadow.appinstance;

import uk.co.littlemike.bitshadow.app.AppUpdate;

public interface AppInstanceUpdate {
    void applyTo(AppInstance appInstance);

    AppUpdate getAppUpdate();

    String getAppName();
}
