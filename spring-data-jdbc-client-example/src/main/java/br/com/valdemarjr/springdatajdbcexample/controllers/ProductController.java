package br.com.valdemarjr.springdatajdbcexample.controllers;

import br.com.valdemarjr.springdatajdbcexample.domain.Product;
import br.com.valdemarjr.springdatajdbcexample.exceptions.ProductNotFoundException;
import br.com.valdemarjr.springdatajdbcexample.services.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Product findBy(@PathVariable Long id) {
    var product = service.findBy(id);
    if (product.isEmpty()) {
      throw new ProductNotFoundException(id);
    }
    return product.get();
  }

  @GetMapping
  public List<Product> findAll() {
    return service.findAll();
  }

  @PostMapping
  public void create(@RequestBody Product product) {
    service.create(product);
  }

  @DeleteMapping("/{id}")
  public void deleteBy(@PathVariable Long id) {
    service.delete(id);
  }

  @PutMapping
  public void update(@RequestBody Product product) {
    service.update(product);
  }
}
