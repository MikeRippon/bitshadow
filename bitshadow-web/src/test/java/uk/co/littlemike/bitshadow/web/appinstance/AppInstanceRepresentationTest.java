package uk.co.littlemike.bitshadow.web.appinstance;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppInstanceRepresentationTest {

    public static final String ID = "Id";

    @Test
    public void mapsToAndFromDomain() {
        AppInstanceRepresentation representation = new AppInstanceRepresentation()
                .withId(ID);

        AppInstanceRepresentation mappedRepresentation = new AppInstanceRepresentation(representation.toDomain());

        assertThat(mappedRepresentation).isEqualToComparingFieldByField(representation);
    }
}
