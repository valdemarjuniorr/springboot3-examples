package br.com.valdemarjr.localstack.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

  @SqsListener(
      value = "${cloud.aws.sqs.notification-queue.url}",
      deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void consume(Message<String> message) {
    log.info("consuming message...");
    log.info("message consumed {}", message);
  }
}
