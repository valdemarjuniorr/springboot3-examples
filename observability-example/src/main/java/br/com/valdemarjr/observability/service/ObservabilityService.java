package br.com.valdemarjr.observability.service;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ObservabilityService {

    @Observed(name = "observabilityService")
    public void get() {
        log.info("get service");
    }
}
