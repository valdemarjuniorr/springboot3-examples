package br.com.valdemarjr.localstack.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @SqsListener(value = "${events.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consume(String message) {
        log.info("consuming message...");
        log.info("message consumed {}", message);
    }
}
