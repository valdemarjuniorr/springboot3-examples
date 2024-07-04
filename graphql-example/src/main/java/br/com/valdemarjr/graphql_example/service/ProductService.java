package br.com.valdemarjr.graphql_example.service;

import br.com.valdemarjr.graphql_example.controllers.Product;
import br.com.valdemarjr.graphql_example.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findBy(Long id) {
    return repository.findById(id).orElseThrow();
  }

  public Product save(String name, BigDecimal price) {
    return repository.save(new Product(null, name, price));
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
