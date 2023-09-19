package br.com.valdemarjr.springsecurityexample.service;

import br.com.valdemarjr.springsecurityexample.domain.User;
import br.com.valdemarjr.springsecurityexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }
}
