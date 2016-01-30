package uk.co.littlemike.bitshadow.web.appinstance;

import uk.co.littlemike.bitshadow.appinstance.AppInstance;
import uk.co.littlemike.bitshadow.appinstance.AppInstanceUpdate;

import java.time.LocalDateTime;

public class RegisterAppInstanceRepresentation implements AppInstanceUpdate {

    private LocalDateTime timeRegistered = LocalDateTime.now();

    @Override
    public void applyTo(AppInstance appInstance) {
        appInstance.setTimeRegistered(timeRegistered);
    }

    public void setTimeRegistered(LocalDateTime timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

}
