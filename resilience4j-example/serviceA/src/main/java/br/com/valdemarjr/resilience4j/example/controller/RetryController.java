package br.com.valdemarjr.resilience4j.example.controller;

import br.com.valdemarjr.resilience4j.example.config.ServicesURLConfig;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/a")
@RequiredArgsConstructor
class RetryController {

  private static final String SERVICE_A = "serviceA";
  private final ServicesURLConfig config;
  private final RestTemplate restTemplate;

  @RequestMapping("/retry")
  @Retry(name = SERVICE_A, fallbackMethod = "fallbackRetry")
  public String retry() {
    log.info("retry method called");
    return restTemplate.getForObject(config.getServiceBUrl(), String.class);
  }

  public String fallbackRetry(Exception ex) {
    log.warn("fallback retry method called. Message {}", ex.getMessage());
    return "fallback retry method called";
  }
}
