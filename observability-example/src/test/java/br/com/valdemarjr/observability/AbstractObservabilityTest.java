package br.com.valdemarjr.observability;

import br.com.valdemarjr.observability.config.ObservedAspectConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Because the whole observability auto-configuration is disabled for tests by default, we need to re-enable it using
 *
 * @AutoConfigureObservability, whenever we want to test the default observations.
 */
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureObservability
@Import({ObservedAspectConfiguration.class, ObservationTestCofiguration.class})
public abstract class AbstractObservabilityTest {
}
