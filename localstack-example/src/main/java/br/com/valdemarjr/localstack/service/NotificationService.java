package br.com.valdemarjr.localstack.service;

import br.com.valdemarjr.localstack.config.EventsConfig;
import br.com.valdemarjr.localstack.domain.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final EventsConfig config;
  private final NotificationMessagingTemplate notificationTemplate;
  private final QueueMessagingTemplate messagingTemplate;

  public void notifyTopic(NotificationMessage message) {
    log.info("Notifying topic {}", config.getTopic˜());
    notificationTemplate.sendNotification(config.getTopic(), message, "notification");
  }

  public void notifyQueue(NotificationMessage message) {
    log.info("Notifying queue {}", config.getQueue());
    messagingTemplate.convertAndSend(config.getQueue(), message);
  }
}
