package br.com.valdemarjr.localstack.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalStackCloudConfig extends AbstractCloudConfig {

  @Value("${cloud.aws.endpoint.uri}")
  private String host;

  @Bean
  @Primary
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(getEndpointConfiguration())
        .withCredentials(getCredentialsProvider())
        .build();
  }

  @Bean
  public AmazonSNS amazonSNSAsync() {
    return AmazonSNSAsyncClientBuilder.standard()
        .withEndpointConfiguration(getEndpointConfiguration())
        .withCredentials(getCredentialsProvider())
        .build();
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate() {
    return new QueueMessagingTemplate(amazonSQSAsync());
  }

  /** this bean is responsible for listening to the queue and must be defined. */
  @Bean
  public QueueMessageHandler queueMessageHandler() {
    var queueMessageHandlerFactory = new QueueMessageHandlerFactory();
    queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsync());
    return queueMessageHandlerFactory.createQueueMessageHandler();
  }

  /** this bean is responsible for listening to the queue and must be defined. */
  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    var simpleListenerContainer = new SimpleMessageListenerContainer();
    simpleListenerContainer.setAmazonSqs(amazonSQSAsync());
    simpleListenerContainer.setMessageHandler(queueMessageHandler());
    return simpleListenerContainer;
  }

  private EndpointConfiguration getEndpointConfiguration() {
    return new EndpointConfiguration(host, region);
  }
}
