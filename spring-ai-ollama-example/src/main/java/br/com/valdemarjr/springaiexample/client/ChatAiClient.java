package br.com.valdemarjr.springaiexample.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class ChatAiClient {

  private static final Logger log = LoggerFactory.getLogger(ChatAiClient.class);

  private final ChatClient chatClient;

  public ChatAiClient(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String question) {
    log.info("chat - question: {}", question);

    var aiResponse = this.chatClient.call(new Prompt(question));
    var response = aiResponse.getResult().getOutput().getContent();

	log.info("chat - question answered: {}", response);
    return response;
  }
}
