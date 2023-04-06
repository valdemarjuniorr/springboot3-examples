package br.com.valdemarjr.localstack.exception;

import br.com.valdemarjr.localstack.domain.NotificationMessage;
import io.awspring.cloud.sqs.listener.errorhandler.ErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Slf4j
@Configuration
public class MessageExceptionHandler implements ErrorHandler<NotificationMessage> {

  @Override
  public void handle(Message<NotificationMessage> message, Throwable t) {
    log.error("An error has been thrown {}", t.getMessage());
    ErrorHandler.super.handle(message, t);
  }
}
