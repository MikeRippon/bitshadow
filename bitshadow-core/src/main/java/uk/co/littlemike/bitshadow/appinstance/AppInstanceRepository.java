package uk.co.littlemike.bitshadow.appinstance;

public interface AppInstanceRepository {
    AppInstance getById(String id);

    AppInstance registerAppInstance(AppInstance instance);
}
