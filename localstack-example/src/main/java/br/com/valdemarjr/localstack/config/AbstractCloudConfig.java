package br.com.valdemarjr.localstack.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;

abstract class AbstractCloudConfig {

  @Value("${cloud.aws.credentials.access-key}")
  protected String accessKeyId;

  @Value("${cloud.aws.credentials.secret-key}")
  protected String secretAccessKey;

  @Value("${cloud.aws.region.static}")
  protected String region;

  protected AWSStaticCredentialsProvider getCredentialsProvider() {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
  }

  @Bean
  public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
    return new NotificationMessagingTemplate(amazonSNS);
  }
}
