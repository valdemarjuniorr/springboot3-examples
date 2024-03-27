package br.com.valdemarjr.redisexample.config;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

import br.com.valdemarjr.redisexample.controllers.Product;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

  @Value("${spring.cache.redis.time-to-live}")
  private long redisTimeToLive;

  @Bean
  LettuceConnectionFactory redisConnectionFactory() {
    var clientConfig = LettuceClientConfiguration.builder().readFrom(REPLICA_PREFERRED).build();

    return new LettuceConnectionFactory(
        new RedisStandaloneConfiguration("localhost", 6379), clientConfig);
  }

  @Bean
  RedisCacheManager cacheManager() {
    return RedisCacheManager.builder(this.redisConnectionFactory())
        .cacheDefaults(this.cacheConfiguration())
        .build();
  }

  @Bean
  RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(redisTimeToLive))
        .disableCachingNullValues()
        .serializeValuesWith(
            SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

  @Bean
  RedisTemplate<Long, Product> redisTemplate(RedisConnectionFactory connectionFactory) {
    var template = new RedisTemplate<Long, Product>();
    template.setConnectionFactory(connectionFactory);

    return template;
  }
}
