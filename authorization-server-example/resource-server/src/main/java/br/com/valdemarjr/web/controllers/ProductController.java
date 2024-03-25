package br.com.valdemarjr.web.controllers;

import br.com.valdemarjr.web.service.ProductService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final AtomicLong counter = new AtomicLong();
  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public Product getProducts() {
    return new Product(counter.get(), service.getProducts() + " " + counter.getAndIncrement());
  }
}
