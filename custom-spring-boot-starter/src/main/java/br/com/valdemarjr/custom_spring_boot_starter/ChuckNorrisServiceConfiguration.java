package br.com.valdemarjr.custom_spring_boot_starter;

import br.com.valdemarjr.custom_spring_boot_starter.jokes.ChuckNorrisAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@EnableConfigurationProperties(ChuckNorrisServiceProperties.class)
public class ChuckNorrisServiceConfiguration {

  private static final Logger log = LoggerFactory.getLogger(ChuckNorrisServiceConfiguration.class);
  private final ChuckNorrisServiceProperties properties;

  public ChuckNorrisServiceConfiguration(ChuckNorrisServiceProperties properties) {
    this.properties = properties;
  }

  /** Using @Bean("chuckNorrisRestClient") just to prevent the default bean name to be created */
  @Bean("chuckNorrisRestClient")
  RestClient restClient(RestClient.Builder builder) {
    log.info("creating RestClient with base url: {}", properties.baseUrl());
    return builder
        .baseUrl(properties.baseUrl())
        .defaultHeaders(
            httpHeaders -> {
              httpHeaders.add("Content-Type", "application/json");
              httpHeaders.add("Accept", "application/json");
            })
        .build();
  }

  @Bean("chuckNorrisAPIClient")
  ChuckNorrisAPIClient client(RestClient restClient) {
    return new ChuckNorrisAPIClient(restClient);
  }
}
