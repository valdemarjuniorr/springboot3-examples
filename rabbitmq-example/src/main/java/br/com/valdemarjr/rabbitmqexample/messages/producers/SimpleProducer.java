package br.com.valdemarjr.rabbitmqexample.messages.producers;

import br.com.valdemarjr.rabbitmqexample.config.QueueConfig;
import br.com.valdemarjr.rabbitmqexample.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class SimpleProducer implements Producer {

  private final QueueConfig config;
  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publish(Message message) {
    var exchange = config.getEventsExchange();
    rabbitTemplate.convertAndSend(exchange, "", message.getContent());
    log.info("message published in {} exchange", exchange);
  }
}
