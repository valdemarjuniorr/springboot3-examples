package br.com.valdemarjr.localstack.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "events")
public class EventsConfig {
  private String topic;
  private String queue;
}
