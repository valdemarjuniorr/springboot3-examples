package br.com.valdemarjr.rabbitmqexample.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class QueueConfigTest {

  @Autowired private QueueConfig config;

  @Test
  void exchangeTest() {
    var exchange = config.exchange();
    Assertions.assertThat(exchange).isNotNull();
    Assertions.assertThat(exchange.getName()).isEqualTo(config.getEventsExchange());
  }

  @Test
  void notifyNewUserQueue() {
    var queue = config.notifyNewUserQueue();
    Assertions.assertThat(queue).isNotNull();
    Assertions.assertThat(queue.getName()).isEqualTo(config.getNewUserQueueName());
  }

  @Test
  void notifyCompanyNewUserQueue() {
    var queue = config.notifyCompanyNewUserQueue();
    Assertions.assertThat(queue).isNotNull();
    Assertions.assertThat(queue.getName()).isEqualTo(config.getNewUserCompanyQueueName());
  }
}
