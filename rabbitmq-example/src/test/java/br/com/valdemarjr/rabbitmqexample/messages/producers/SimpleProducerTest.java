package br.com.valdemarjr.rabbitmqexample.messages.producers;

import br.com.valdemarjr.rabbitmqexample.config.QueueConfig;
import br.com.valdemarjr.rabbitmqexample.domain.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleProducerTest {

    private static final String EXCHANGE_KEY = "exchange.key";
    private static final String CONTENT = "message content";
    @Mock
    private QueueConfig config;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private SimpleProducer producer;

  @Test
  void publish() {
      when(config.getEventsExchange()).thenReturn(EXCHANGE_KEY);
      doNothing().when(rabbitTemplate).convertAndSend(EXCHANGE_KEY, "", CONTENT);

      producer.publish(new Message(CONTENT));

      verify(config).getEventsExchange();
      verify(rabbitTemplate).convertAndSend(EXCHANGE_KEY, "", CONTENT);
  }
}
