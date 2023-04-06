package br.com.valdemarjr.localstack.service;

import br.com.valdemarjr.localstack.config.EventsConfig;
import br.com.valdemarjr.localstack.domain.NotificationMessage;
import io.awspring.cloud.sns.core.SnsOperations;
import io.awspring.cloud.sns.core.SnsTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  private final SnsTemplate snsTemplate;

  /**
   * In such case, we recommend using SnsOperations - an interface implemented by SnsTemplate, that
   * exposes a convenient method for sending SNS notification, including support for FIFO topics.
   */
  private final SnsOperations snsOperations;

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
//    sqsTemplate.send(config.getQueue(), message);
  }
}
