package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
class UserResponse {

  private String id;
  private String name;
  private Integer age;
  private List<HobbyResponse> hobbies = new ArrayList<>();

  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.age = user.getAge();
    this.hobbies = user.getHobbies().stream().map(HobbyResponse::new).collect(Collectors.toList());
  }
}
