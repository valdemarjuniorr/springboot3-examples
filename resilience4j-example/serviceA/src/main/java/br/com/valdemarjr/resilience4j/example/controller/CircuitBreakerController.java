package br.com.valdemarjr.resilience4j.example.controller;

import br.com.valdemarjr.resilience4j.example.config.ServicesURLConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/a")
@RequiredArgsConstructor
class CircuitBreakerController {

  private static final String SERVICE_A = "serviceA";
  private final ServicesURLConfig config;
  private final RestTemplate restTemplate;

  @GetMapping
  @CircuitBreaker(name = SERVICE_A, fallbackMethod = "callBackMethod")
  public String ServiceA() {
    log.info("Service A called");
    return restTemplate.getForObject(config.getServiceBUrl(), String.class);
  }

  /** The fallback method must be declared in the same class */
  public String callBackMethod(Exception ex) {
    log.error("error {}", ex.getMessage());
    return "This is a fallaback method from Service A";
  }
}
