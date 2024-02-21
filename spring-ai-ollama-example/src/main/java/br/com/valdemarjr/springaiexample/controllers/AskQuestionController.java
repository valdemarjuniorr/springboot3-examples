package br.com.valdemarjr.springaiexample.controllers;

import br.com.valdemarjr.springaiexample.client.ChatAiClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskQuestionController {

  private final ChatAiClient chatClient;

  public AskQuestionController(ChatAiClient chatClient) {
    this.chatClient = chatClient;
  }

  @PostMapping("/ask")
  public Response ask(@RequestBody QuestionRequest questionRequest) {
    return new Response(chatClient.chat(questionRequest.question()));
  }
}
