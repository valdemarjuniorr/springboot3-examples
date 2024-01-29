package br.com.valdemarjr.restclienttestexample.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import br.com.valdemarjr.restclienttestexample.domain.JokeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;

@RestClientTest(ChuckNorrisJokesController.class)
class ChuckNorrisJokesControllerTest {

  private static final String URL = "https://api.chucknorris.io/jokes/random";

  @Autowired ChuckNorrisJokesController controller;

  @Autowired MockRestServiceServer server;

  @Autowired ObjectMapper objectMapper;

  @Test
  void getJoke() throws JsonProcessingException {
    // given
    var joke =
        new JokeResponse(
            "0PvaPGmKSNuRXLQPYiAiOQ",
            "https://api.chucknorris.io/jokes/0PvaPGmKSNuRXLQPYiAiOQ",
            "Those who say you can't unring a bell never met Chuck Norris.");
    var json = objectMapper.writeValueAsString(joke);

    // when
    server.expect(requestTo(URL)).andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    // then
    var response = controller.getJoke();
    assertThat(response).isEqualTo(joke);
  }

  @Test
  void getJokeWithException() {
    // when
    server.expect(requestTo(URL)).andRespond(withBadRequest());

    // then
    assertThatThrownBy(() -> controller.getJoke())
        .isInstanceOf(HttpClientErrorException.BadRequest.class);
  }
}
