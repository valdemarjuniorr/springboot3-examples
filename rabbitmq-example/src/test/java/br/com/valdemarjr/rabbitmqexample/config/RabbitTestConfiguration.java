package br.com.valdemarjr.rabbitmqexample.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@RabbitListenerTest(capture = true)
public class RabbitTestConfiguration {

  @Bean
  public TestRabbitTemplate testRabbitTemplate() throws Exception {
    return new TestRabbitTemplate(mockConnectionFactory());
  }

  @Bean
  public ConnectionFactory mockConnectionFactory() throws Exception {
    var factory = Mockito.mock(ConnectionFactory.class);
    var connection = Mockito.mock(Connection.class);
    var channel = Mockito.mock(Channel.class);
    BDDMockito.willReturn(connection).given(factory).createConnection();
    BDDMockito.willReturn(channel).given(connection).createChannel(Mockito.anyInt());
    BDDMockito.given(channel.isOpen()).willReturn(true);
    return factory;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() throws Exception {
    var factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(mockConnectionFactory());
    return factory;
  }
}
