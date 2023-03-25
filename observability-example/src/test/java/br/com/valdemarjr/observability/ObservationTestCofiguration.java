package br.com.valdemarjr.observability;

import io.micrometer.observation.tck.TestObservationRegistry;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ObservationTestCofiguration {

    @Bean
    public TestObservationRegistry observationRegistry() {
        return TestObservationRegistry.create();
    }
}
