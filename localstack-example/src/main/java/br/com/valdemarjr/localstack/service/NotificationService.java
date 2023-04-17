package br.com.valdemarjr.localstack.service;

import br.com.valdemarjr.localstack.config.EventsConfig;
import br.com.valdemarjr.localstack.domain.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final EventsConfig config;

  /**
   * Because of Spring Messaging compatibility, SnsTemplate exposes many methods that you may not
   * need if you don’t need Spring Messaging abstractions
   */
  private final NotificationMessagingTemplate snsTemplate;

  private final QueueMessagingTemplate sqsTemplate;

  //  private final SqsTemplate sqsTemplate;

  void sendNotification() {
    // sends String payload
    snsTemplate.sendNotification("topic-arn", "payload", "subject");
    // sends object serialized to JSON
    //    snsTemplate.sendNotification("topic-arn", new Person("John", "Doe"), "subject");
    // sends a Spring Messaging Message
    var message =
        MessageBuilder.withPayload("payload").setHeader("header-name", "header-value").build();
    snsTemplate.send("topic-arn", message);
  }

  public void notifyTopic(NotificationMessage message) {
    log.info("Notifying topic {}", config.getTopic());
    snsTemplate.sendNotification(config.getTopic(), message, "notification");
  }

  public void notifyQueue(NotificationMessage message) {
    log.info("Notifying queue {}", config.getQueue());
    sqsTemplate.convertAndSend(config.getQueue(), message);
  }
}
