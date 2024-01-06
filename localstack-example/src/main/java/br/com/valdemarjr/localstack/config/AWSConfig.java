package br.com.valdemarjr.localstack.config;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("aws")
public class AWSConfig extends AbstractCloudConfig {

  @Bean
  @Primary
  public AmazonSQSAsync amazonSQSClient() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withCredentials(getCredentialsProvider())
        .withRegion(region)
        .build();
  }

  @Bean
  public AmazonSNS snsClient() {
    return AmazonSNSClientBuilder.standard()
        .withRegion(region)
        .withCredentials(getCredentialsProvider())
        .build();
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate() {
    return new QueueMessagingTemplate(amazonSQSClient());
  }

  /** this bean is responsible for listening to the queue and must be defined. */
  @Bean
  public QueueMessageHandler queueMessageHandler() {
    var queueHandlerFactory = new QueueMessageHandlerFactory();
    queueHandlerFactory.setAmazonSqs(amazonSQSClient());
    return queueHandlerFactory.createQueueMessageHandler();
  }

  /** this bean is responsible for listening to the queue and must be defined. */
  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    var listenerContainer = new SimpleMessageListenerContainer();
    listenerContainer.setAmazonSqs(amazonSQSClient());
    listenerContainer.setMessageHandler(queueMessageHandler());
    return listenerContainer;
  }
}
