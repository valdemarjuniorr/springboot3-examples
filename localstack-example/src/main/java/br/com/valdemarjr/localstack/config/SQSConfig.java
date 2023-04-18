package br.com.valdemarjr.localstack.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SQSConfig {

  @Value("${cloud.aws.credentials.access-key}")
  private String accessKeyId;

  @Value("${cloud.aws.credentials.secret-key}")
  private String secretAccessKey;

  @Value("${cloud.aws.endpoint.uri}")
  private String host;

  @Value("${cloud.aws.region.static}")
  private String region;

  @Bean
  @Primary
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(getEndpointConfiguration())
        .withCredentials(getCredentialsProvider())
        .build();
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate() {
    return new QueueMessagingTemplate(amazonSQSAsync());
  }

  @Bean
  public AmazonSNS amazonSNSAsync() {
    return AmazonSNSAsyncClientBuilder.standard()
        .withEndpointConfiguration(getEndpointConfiguration())
        .withCredentials(getCredentialsProvider())
        .build();
  }

  @Bean
  public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
    return new NotificationMessagingTemplate(amazonSNS);
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

  private AWSStaticCredentialsProvider getCredentialsProvider() {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
  }

  private EndpointConfiguration getEndpointConfiguration() {
    return new EndpointConfiguration(host, region);
  }
}
