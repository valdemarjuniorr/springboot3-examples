package br.com.valdemarjr.redisexample.controllers;

import br.com.valdemarjr.redisexample.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public void save(@RequestBody Product product) {
    productService.save(product);
  }

  @GetMapping("/{id}")
  public Product getBy(@PathVariable Long id) {
    return productService.getBy(id);
  }

  @DeleteMapping("/{id}")
  public void deleteBy(@PathVariable Long id) {
    productService.deleteBy(id);
  }
}
