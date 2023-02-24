package br.com.valdemarjr.mongodbcrudexample.service;

import br.com.valdemarjr.mongodbcrudexample.domain.User;
import br.com.valdemarjr.mongodbcrudexample.exceptions.UserNotFoundException;
import br.com.valdemarjr.mongodbcrudexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public Optional<User> findBy(String id) {
    return repository.findById(id);
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User save(User user) {
    return repository.save(user);
  }

  public void update(User user) {
    var userFound = findBy(user.getId()).orElseThrow(UserNotFoundException::new);
    userFound.setName(user.getName());
    userFound.setAge(user.getAge());
    userFound.setHobbies(user.getHobbies());
    repository.save(userFound);
  }

  public User delete(String id) {
    var userFound = findBy(id).orElseThrow(UserNotFoundException::new);
    repository.delete(userFound);
    return userFound;
  }
}
