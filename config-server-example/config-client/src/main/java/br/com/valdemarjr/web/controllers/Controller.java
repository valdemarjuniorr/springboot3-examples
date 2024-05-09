package br.com.valdemarjr.web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class Controller {

  private final RestClient restClient;

  /** The value is get from config-server */
  @Value("${api.chucknorris}")
  private String apiUrl;

  public Controller() {
    this.restClient = RestClient.create();
  }

  @GetMapping
  String get() {
    return restClient.get().uri(apiUrl).retrieve().body(String.class);
  }
}
