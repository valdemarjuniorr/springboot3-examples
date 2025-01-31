package br.com.valdemarjr.springaiexample.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Component;

@Component
public class ChatAiClient {

  private static final Logger log = LoggerFactory.getLogger(ChatAiClient.class);
  private final ChatClient chatClient;

  public ChatAiClient(OllamaChatModel model) {
    this.chatClient = ChatClient.create(model);
  }

  public String chat(String question) {
    log.info("chat - question: {}", question);

    var aiResponse = this.chatClient.prompt(question).call().content();

	log.info("chat - question answered: {}", aiResponse);

    return aiResponse;
  }
}
