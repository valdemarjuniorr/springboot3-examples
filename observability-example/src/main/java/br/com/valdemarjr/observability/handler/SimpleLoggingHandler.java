package br.com.valdemarjr.observability.handler;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleLoggingHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onStart(Observation.Context context) {
        log.info("onStart called");
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        log.info("onScopeOpened called");
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        log.info("onScopeClosed called");
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("onStop called");
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("onError called");
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        log.info("onEvent called");
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        log.info("onScopeReset called");
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
