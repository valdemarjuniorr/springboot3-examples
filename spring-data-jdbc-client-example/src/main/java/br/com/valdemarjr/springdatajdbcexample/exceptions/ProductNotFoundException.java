package br.com.valdemarjr.springdatajdbcexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Product not found", code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Product not found with id " + id);
  }
}
