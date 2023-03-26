package br.com.valdemarjr.websocket.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "websocket")
class WebsocketConfig {
  private String broker;
  private String destinationPrefixes;
  private String stompEndpoint;
}
