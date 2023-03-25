package br.com.valdemarjr.observability.service;

import br.com.valdemarjr.observability.AbstractObservabilityTest;
import io.micrometer.observation.tck.TestObservationRegistry;
import io.micrometer.observation.tck.TestObservationRegistryAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ObservabilityServiceTest extends AbstractObservabilityTest {

    @Autowired
    private ObservabilityService service;

    @Autowired
    private TestObservationRegistry registry;

    @Test
    void get() {
        service.get();
        TestObservationRegistryAssert.assertThat(registry)
                .hasObservationWithNameEqualTo("observabilityService")
                .that().hasBeenStarted().hasBeenStopped();
    }
}

