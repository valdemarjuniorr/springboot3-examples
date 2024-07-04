package br.com.valdemarjr.graphql_example.controllers;

import br.com.valdemarjr.graphql_example.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @QueryMapping
  List<Product> findAll() {
    return service.findAll();
  }

  @QueryMapping
  Product findById(@Argument Long id) {
    return service.findBy(id);
  }

  @MutationMapping
  Product save(@Argument String name, @Argument BigDecimal price) {
    return service.save(name, price);
  }

  @MutationMapping
  Long delete(@Argument Long id) {
    service.delete(id);
    return id;
  }
}
