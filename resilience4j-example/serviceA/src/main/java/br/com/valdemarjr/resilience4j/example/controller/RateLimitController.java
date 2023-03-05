package br.com.valdemarjr.resilience4j.example.controller;

import br.com.valdemarjr.resilience4j.example.config.ServicesURLConfig;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/a")
@RequiredArgsConstructor
class RateLimitController {

  private static final String SERVICE_A = "serviceA";
  private final ServicesURLConfig config;
  private final RestTemplate restTemplate;

  @RequestMapping("/rate-limit")
  @RateLimiter(name = SERVICE_A, fallbackMethod = "fallbackRateLimit")
  public String rateLimit() {
    log.info("rateLimit method called");
    return restTemplate.getForObject(config.getServiceBUrl(), String.class);
  }

  public String fallbackRateLimit(Exception ex) {
    log.warn("rate limit achieved. Message {}", ex.getMessage());
    return "Rate limit achieved. Try again in 3 seconds";
  }
}
