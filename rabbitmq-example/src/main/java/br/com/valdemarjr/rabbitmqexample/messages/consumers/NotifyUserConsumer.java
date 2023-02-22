package br.com.valdemarjr.rabbitmqexample.messages.consumers;

import br.com.valdemarjr.rabbitmqexample.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotifyUserConsumer implements Consumer {

  @Override
  @RabbitListener(queues = "${events.queues.new.user}")
  public void consume(Message message) {
    log.info("New user received a new message. Message -> {}", message.getContent());
  }
}
