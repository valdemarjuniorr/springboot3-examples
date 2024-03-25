package br.com.valdemarjr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OAuthClientApplication {

  private static final String RESOURCE_SERVER_URL = "http://localhost:8081/";

  public static void main(String[] args) {
    SpringApplication.run(OAuthClientApplication.class, args);
  }

  @Bean
  public RouteLocator gateway(RouteLocatorBuilder rlb) {
    return rlb.routes()
        .route(r -> r.path("/**").filters(GatewayFilterSpec::tokenRelay).uri(RESOURCE_SERVER_URL))
        .build();
  }
}
