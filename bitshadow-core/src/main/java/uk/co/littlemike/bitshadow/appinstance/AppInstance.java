package uk.co.littlemike.bitshadow.appinstance;

public class AppInstance {
    private String id;

    public AppInstance withId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }
}
