package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AmqpIntegrationConfiguration {

  static final String ORDERS_Q = "orders";

  @Bean
  Binding binding(Queue queue, Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ORDERS_Q).noargs();
  }

  @Bean
  Exchange exchange() {
    return ExchangeBuilder.directExchange(ORDERS_Q).build();
  }

  @Bean
  Queue queue() {
    return QueueBuilder.durable(ORDERS_Q).build();
  }
}
