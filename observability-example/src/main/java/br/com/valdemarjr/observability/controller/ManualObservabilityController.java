package br.com.valdemarjr.observability.controller;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manual")
public class ManualObservabilityController {

    private final ObservationRegistry observationRegistry;

    @GetMapping
    public void getManual() {
        var observation = Observation.createNotStarted("manual", observationRegistry);
        observation.start();
        try (var ignored = observation.openScope()) {
            log.info("get manual called");
        } catch (Exception ex) {
            log.error("error to call method", ex);
            observation.error(ex);
        } finally {
            observation.stop();
        }
    }

    @GetMapping("/simpler")
    public void getManualSimpler() {
        var observation = Observation.createNotStarted("manual_simpler", observationRegistry);
        observation.observe(() -> log.info("get manual simpler called"));
    }
}
