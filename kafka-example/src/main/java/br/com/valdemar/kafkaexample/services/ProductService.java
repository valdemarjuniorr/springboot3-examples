package br.com.valdemar.kafkaexample.services;

import br.com.valdemar.kafkaexample.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private static Logger log = LoggerFactory.getLogger(ProductService.class);
  private final KafkaTemplate<String, Product> kafkaTemplate;

  @Value("${kafka.topic}")
  private String topic;

  public ProductService(KafkaTemplate<String, Product> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Product product) {
    log.info("Sending message {}", product);
    kafkaTemplate.send(topic, product);
    log.info("Message sent");
  }

  @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
  public void consumeMessage(Product product) {
    log.info("Message consumed {}", product);
  }
}
