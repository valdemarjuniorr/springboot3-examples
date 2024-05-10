package br.com.valdemarjr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  private final RestClient restClient;
  private final Environment environment;

  public Controller(Environment environment) {
    this.environment = environment;
    this.restClient = RestClient.create();
  }

  @EventListener({ApplicationReadyEvent.class, RefreshScopeRefreshedEvent.class})
  public void onApplicationReady() {
    log.info("Environment {}", environment);
  }

  @GetMapping
  String get() {
    var apiUrl = this.environment.getProperty("api.chucknorris");
    log.info("API URL {}", apiUrl);
    return restClient.get().uri(apiUrl).retrieve().body(String.class);
  }
}
