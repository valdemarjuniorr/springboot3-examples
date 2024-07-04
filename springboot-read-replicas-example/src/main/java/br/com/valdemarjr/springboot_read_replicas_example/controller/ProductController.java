package br.com.valdemarjr.springboot_read_replicas_example.controller;

import br.com.valdemarjr.springboot_read_replicas_example.domain.Product;
import br.com.valdemarjr.springboot_read_replicas_example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public List<Product> findAll() {
    return service.findAll();
  }

  @PostMapping
  public Product save(Product product) {
    return service.save(product);
  }
}
