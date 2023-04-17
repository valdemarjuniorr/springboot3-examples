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
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableSqs
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

  @Bean
  public QueueMessageHandler queueMessageHandle1111r() {
    QueueMessageHandlerFactory queueMessageHandlerFactory = new QueueMessageHandlerFactory();
    queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsync());
    QueueMessageHandler queueMessageHandler = queueMessageHandlerFactory.createQueueMessageHandler();
    return queueMessageHandler;
  }

  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer111(AmazonSQSAsync amazonSQSAsync) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setAmazonSqs(amazonSQSAsync);
    simpleMessageListenerContainer.setMessageHandler(queueMessageHandle1111r());
    simpleMessageListenerContainer.setMaxNumberOfMessages(10);
//    simpleMessageListenerContainer.setTaskExecutor(threadPoolTaskExecutor());
    return simpleMessageListenerContainer;
  }

  private AWSStaticCredentialsProvider getCredentialsProvider() {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
  }

  private EndpointConfiguration getEndpointConfiguration() {
    return new EndpointConfiguration(host, region);
  }
}
