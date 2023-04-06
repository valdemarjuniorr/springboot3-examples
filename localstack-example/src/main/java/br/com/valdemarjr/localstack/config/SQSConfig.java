package br.com.valdemarjr.localstack.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.Collections;

@Configuration
public class SQSConfig {

  @Bean
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("ANUJDEKAVADIYAEXAMPLE", "2QvM4/Tdmf38SkcD/qalvXO4EXAMPLEKEY")))
            .build();
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
    return new QueueMessagingTemplate(amazonSQSAsync);
  }

  @Bean
  public QueueMessageHandlerFactory queueMessageHandlerFactory(ObjectMapper mapper,
                                                               AmazonSQSAsync amazonSQSAsync) {
    var queueHandlerFactory = new QueueMessageHandlerFactory();
    queueHandlerFactory.setAmazonSqs(amazonSQSAsync);
    queueHandlerFactory.setArgumentResolvers(
            Collections.singletonList(new PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))));
    return queueHandlerFactory;
  }

  private MessageConverter jackson2MessageConverter(ObjectMapper mapper) {
    var converter = new MappingJackson2MessageConverter();
    // set strict content type match to false to enable the listener to handle AWS events
    converter.setStrictContentTypeMatch(false);
    converter.setObjectMapper(mapper);
    return converter;
  }
}
