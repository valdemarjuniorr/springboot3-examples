package br.com.valdemarjr.localstack.controller;

import io.awspring.cloud.sns.annotation.endpoint.NotificationMessageMapping;
import io.awspring.cloud.sns.annotation.endpoint.NotificationSubscriptionMapping;
import io.awspring.cloud.sns.annotation.endpoint.NotificationUnsubscribeConfirmationMapping;
import io.awspring.cloud.sns.annotation.handlers.NotificationMessage;
import io.awspring.cloud.sns.annotation.handlers.NotificationSubject;
import io.awspring.cloud.sns.handlers.NotificationStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://docs.awspring.io/spring-cloud-aws/docs/3.0.0-RC1/reference/html/index.html#annotation-driven-http-notification-endpoint
 */
@Controller
@RequestMapping("/topicName")
public class AnnotationNotificationController {

  @NotificationSubscriptionMapping
  public void handleSubscriptionMessage(NotificationStatus status) {
    // We subscribe to start receive the message
    status.confirmSubscription();
  }

  @NotificationMessageMapping
  public void handleNotificationMessage(
      @NotificationSubject String subject, @NotificationMessage String message) {
    // ...
  }

  @NotificationUnsubscribeConfirmationMapping
  public void handleUnsubscribeMessage(NotificationStatus status) {
    // e.g. the client has been unsubscribed and we want to "re-subscribe"
    status.confirmSubscription();
  }
}
