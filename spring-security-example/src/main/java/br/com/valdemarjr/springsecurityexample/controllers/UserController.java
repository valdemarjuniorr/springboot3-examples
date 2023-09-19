package br.com.valdemarjr.springsecurityexample.controllers;

import br.com.valdemarjr.springsecurityexample.domain.User;
import br.com.valdemarjr.springsecurityexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  @RequestMapping
  public List<User> findAll() {
    return service.findAll();
  }
}
