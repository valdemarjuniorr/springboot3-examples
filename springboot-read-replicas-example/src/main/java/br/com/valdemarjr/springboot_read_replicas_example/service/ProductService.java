package br.com.valdemarjr.springboot_read_replicas_example.service;

import br.com.valdemarjr.springboot_read_replicas_example.domain.Product;
import br.com.valdemarjr.springboot_read_replicas_example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public List<Product> findAll() {
    return repository.findAll();
  }

  @Transactional
  public Product save(Product product) {
    return repository.save(product);
  }
}
