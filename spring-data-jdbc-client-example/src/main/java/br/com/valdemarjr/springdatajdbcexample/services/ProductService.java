package br.com.valdemarjr.springdatajdbcexample.services;

import br.com.valdemarjr.springdatajdbcexample.domain.Product;
import br.com.valdemarjr.springdatajdbcexample.exceptions.ProductNotFoundException;
import br.com.valdemarjr.springdatajdbcexample.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Optional<Product> findBy(Long id) {
    return repository.findBy(id);
  }

  public void create(Product product) {
    var create = repository.create(product);
    Assert.state(create == 1, "Failed to create product " + product.description());
  }

  public void delete(Long id) {
    var delete = repository.delete(id);
    if (delete == 0) {
      throw new ProductNotFoundException(id);
    }
  }

  public void update(Product product) {
    var update = repository.update(product);
    if (update == 0) {
      throw new ProductNotFoundException(product.id());
    }
  }
}
