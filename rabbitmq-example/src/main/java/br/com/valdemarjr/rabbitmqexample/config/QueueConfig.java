package br.com.valdemarjr.rabbitmqexample.config;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Getter
@Configuration
public class QueueConfig {

  @Value("${events.exchange}")
  private String eventsExchange;

  @Value("${events.queues.new.user}")
  private String newUserQueueName;

  @Value("${events.queues.company.new.user}")
  private String newUserCompanyQueueName;

  @Bean
  FanoutExchange exchange() {
    return new FanoutExchange(eventsExchange);
  }

  @Bean
  Queue notifyNewUserQueue() {
    return new Queue(newUserQueueName, Boolean.TRUE);
  }

  @Bean
  Queue notifyCompanyNewUserQueue() {
    return new Queue(newUserCompanyQueueName, Boolean.TRUE);
  }

  @Bean
  Binding notifiNewUserBinding(Queue notifyNewUserQueue, FanoutExchange exchange) {
    return BindingBuilder.bind(notifyNewUserQueue).to(exchange);
  }

  @Bean
  Binding notifyCompanyBinding(Queue notifyCompanyNewUserQueue, FanoutExchange exchange) {
    return BindingBuilder.bind(notifyCompanyNewUserQueue).to(exchange);
  }

  @Bean
  MappingJackson2MessageConverter consumerJackson2MessageConverter() {
    return new MappingJackson2MessageConverter();
  }
}
