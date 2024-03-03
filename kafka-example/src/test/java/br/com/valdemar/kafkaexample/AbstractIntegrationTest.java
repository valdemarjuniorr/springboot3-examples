package br.com.valdemar.kafkaexample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@EmbeddedKafka
@SpringBootTest
public abstract class AbstractIntegrationTest {

  /**
   * The group id must be different for each test class, because another consumer could consume the
   * message
   */
  private static final String TEST_GROUP_ID = "test-group-id";

  @Autowired protected EmbeddedKafkaBroker embeddedKafkaBroker;
  protected KafkaMessageListenerContainer<String, String> container;
  protected BlockingQueue<ConsumerRecord<String, String>> consumerRecords;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    consumerRecords = new LinkedBlockingQueue<>();
    var consumerProperties =
        KafkaTestUtils.consumerProps(TEST_GROUP_ID, "false", embeddedKafkaBroker);
    var consumer = new DefaultKafkaConsumerFactory<String, String>(consumerProperties);

    var containerProperties = new ContainerProperties(topicToBeTested());
    container = new KafkaMessageListenerContainer<>(consumer, containerProperties);
    container.setupMessageListener(
        (MessageListener<String, String>) record -> consumerRecords.add(record));
    container.start();
    ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
  }

  @AfterEach
  void after() {
    container.stop();
  }

  /**
   * Utility method for converting an object to a JSON string
   */
  protected String asJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method responsible for returning the topic to be tested
   */
  protected abstract String topicToBeTested();
}
