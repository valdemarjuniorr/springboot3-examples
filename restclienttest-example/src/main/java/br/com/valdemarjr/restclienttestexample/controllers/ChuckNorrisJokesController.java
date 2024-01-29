package br.com.valdemarjr.restclienttestexample.controllers;

import br.com.valdemarjr.restclienttestexample.domain.JokeResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping
public class ChuckNorrisJokesController {

  private final RestClient restClient;

  public ChuckNorrisJokesController(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("https://api.chucknorris.io/jokes/random").build();
  }

  @GetMapping
  public JokeResponse getJoke() {
    return this.restClient.get().retrieve().body(new ParameterizedTypeReference<>() {});
  }
}
