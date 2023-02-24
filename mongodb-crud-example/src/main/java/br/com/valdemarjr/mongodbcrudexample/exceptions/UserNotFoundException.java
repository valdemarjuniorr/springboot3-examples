package br.com.valdemarjr.mongodbcrudexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user not found")
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("user not found");
  }
}
