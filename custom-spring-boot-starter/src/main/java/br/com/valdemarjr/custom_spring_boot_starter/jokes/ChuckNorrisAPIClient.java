package br.com.valdemarjr.custom_spring_boot_starter.jokes;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

public class ChuckNorrisAPIClient {

  private final Logger log = LoggerFactory.getLogger(ChuckNorrisAPIClient.class);
  private final RestClient restClient;

  public ChuckNorrisAPIClient(RestClient restClient) {
    this.restClient = restClient;
  }

  public ChuckNorrisJoke getRandomJoke() {
    log.info("getting random joke from Chuck Norris API");
    return this.restClient.get().uri("/random").retrieve().body(ChuckNorrisJoke.class);
  }

  public List<String> getCategories() {
    log.info("getting categories from Chuck Norris API");
    return this.restClient
        .get()
        .uri("/categories")
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}
