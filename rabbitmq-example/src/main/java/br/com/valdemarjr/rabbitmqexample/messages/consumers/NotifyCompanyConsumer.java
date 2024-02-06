package br.com.valdemarjr.rabbitmqexample.messages.consumers;

import br.com.valdemarjr.rabbitmqexample.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotifyCompanyConsumer implements Consumer {

  @Override
  @RabbitListener(id = "notify-company", queues = "${events.queues.company.new.user}")
  public void consume(Message message) {
    log.info("A company received a new user message. Message -> {}", message.getContent());
  }
}
