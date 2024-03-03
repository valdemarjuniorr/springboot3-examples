package br.com.valdemar.kafkaexample.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import br.com.valdemar.kafkaexample.AbstractIntegrationTest;
import br.com.valdemar.kafkaexample.domain.Product;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class ProductServiceTest extends AbstractIntegrationTest {

  @Value("${kafka.topic}")
  private String topic;

  @Autowired private ProductService service;

  @Test
  void sendMessage() throws InterruptedException {
    // given
    var product = new Product(1L, "Product 1");
    // when
    service.sendMessage(product);
    // then
    var consumedRecord = consumerRecords.poll(1, TimeUnit.SECONDS);
    assertThat(consumedRecord.topic()).isEqualTo(topicToBeTested());
    assertThat(consumedRecord.key()).isNull();
    assertThat(consumedRecord.value()).isEqualTo(asJson(product));
  }

  @Override
  protected String topicToBeTested() {
    return topic;
  }
}
