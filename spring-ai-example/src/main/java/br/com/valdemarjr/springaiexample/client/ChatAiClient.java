package br.com.valdemarjr.springaiexample.client;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class ChatAiClient {

  private static final Logger log = LoggerFactory.getLogger(ChatAiClient.class);

  private final VectorStore vectorStore;
  private final ChatClient chatClient;

  public ChatAiClient(VectorStore vectorStore, ChatClient.Builder builder) {
    this.vectorStore = vectorStore;
    this.chatClient = builder.build();
  }

  public String chat(String question) {
    log.info("chat - question:{}", question);
    var template =
        """

            You're assisting with questions about services offered by Carina.
            Carina is a two-sided healthcare marketplace focusing on home care aides (caregivers)
            and their Medicaid in-home care clients (adults and children with developmental disabilities and low income elderly population).
            Carina's mission is to build online tools to bring good jobs to care workers, so care workers can provide the
            best possible care for those who need it.

            Use the information from the DOCUMENTS section to provide accurate answers but act as if you knew this information innately.
            If unsure, simply state that you don't know.

            DOCUMENTS:
            {documents}

            """;
    var listOfSimilarDocs = this.vectorStore.similaritySearch(question);
    var docs =
        listOfSimilarDocs.stream()
            .map(Document::getText)
            .collect(Collectors.joining(System.lineSeparator()));

    var systemMessage = new SystemPromptTemplate(template).createMessage(Map.of("documents", docs));
    var userMessage = new UserMessage(question);
    var promptList = new Prompt(List.of(systemMessage, userMessage));

    return this.chatClient.prompt(promptList).call().content();

  }
}
