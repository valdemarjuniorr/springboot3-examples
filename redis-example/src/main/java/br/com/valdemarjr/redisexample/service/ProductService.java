package br.com.valdemarjr.redisexample.service;

import br.com.valdemarjr.redisexample.controllers.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final RedisTemplate<Long, Product> redisTemplate;

  public ProductService(RedisTemplate<Long, Product> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void save(Product product) {
    redisTemplate.opsForValue().set(product.id(), product);
  }

  @Cacheable(value = "products", key = "#id")
  public Product getBy(Long id) {
    return redisTemplate.opsForValue().get(id);
  }

  public void deleteBy(Long id) {
    redisTemplate.opsForValue().getOperations().delete(id);
  }
}
