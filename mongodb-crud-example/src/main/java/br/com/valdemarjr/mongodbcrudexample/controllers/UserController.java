package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.exceptions.UserNotFoundException;
import br.com.valdemarjr.mongodbcrudexample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public UserResponse findBy(@PathVariable("id") String id) {
    var user = userService.findBy(id);
    return new UserResponse(user.orElseThrow(UserNotFoundException::new));
  }

  @GetMapping
  public List<UserResponse> findAll() {
    var users = userService.findAll();
    return users.stream().map(UserResponse::new).collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String save(@RequestBody UserRequest request) {
    var saved = userService.save(request.toEntity());
    log.info("new user saved by id {}", saved.getId());
    return saved.getId();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void update(@PathVariable("id") String id, @RequestBody UserRequest request) {
    var user = request.toEntity();
    user.setId(id);
    userService.update(user);
    log.info("user id {} updated", id);
  }

  @DeleteMapping("/{id}")
  public UserResponse delete(@PathVariable("id") String id) {
    var deleted = userService.delete(id);
    log.info("user id {} deleted", id);
    return new UserResponse(deleted);
  }
}
