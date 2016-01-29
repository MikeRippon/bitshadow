package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstance.AppInstance;

import static org.assertj.core.api.Assertions.assertThat;

public class AppInstanceRepresentationTest {

    public static final String ID = "Id";

    @Test
    public void mapsToAndFromDomain() {
        AppInstance instance = new AppInstance(ID);

        AppInstance mappedInstance = new AppInstanceRepresentation(instance).toDomain();

        assertThat(mappedInstance).isEqualToComparingFieldByField(instance);
    }
}
