package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class UserRequest {

  private String id;
  private String name;

  private Integer age;
  private List<HobbyRequest> hobbies;

  public User toEntity() {
    return User.builder()
        .id(this.id)
        .name(this.name)
        .age(this.age)
        .hobbies(this.hobbies.stream().map(HobbyRequest::toEntity).collect(Collectors.toList()))
        .build();
  }
}
