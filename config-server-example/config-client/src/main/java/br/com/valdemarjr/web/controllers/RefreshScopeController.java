package br.com.valdemarjr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * @RefreshScope is used to refresh the bean when the /actuator/refresh endpoint is called
 */
@RefreshScope
@RestController
public class RefreshScopeController {

  private static final Logger log = LoggerFactory.getLogger(RefreshScopeController.class);

  private final RestClient restClient;

  /**
   * to use @Value annotation and make the value change toward config-server refresh, it is
   * necessary to add @RefreshScope in the bean
   */
  @Value("${api.httpbin}")
  private String apiUrl;

  public RefreshScopeController() {
    this.restClient = RestClient.create();
  }

  @GetMapping("/refresh")
  String get() {
    log.info("API URL {}", apiUrl);
    return restClient.get().uri(apiUrl).retrieve().body(String.class);
  }
}
