package uk.co.littlemike.bitshadow.web.app;

import org.junit.Test;
import uk.co.littlemike.bitshadow.app.App;

import static org.assertj.core.api.Assertions.assertThat;

public class AppRepresentationTest {
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "A description";

    @Test
    public void mapsFromDomain() {
        App app = new App(NAME);
        app.setDescription(DESCRIPTION);

        AppRepresentation representation = new AppRepresentation(app);

        assertThat(representation.getName()).isEqualTo(NAME);
        assertThat(representation.getDescription()).isEqualTo(DESCRIPTION);
    }
}
