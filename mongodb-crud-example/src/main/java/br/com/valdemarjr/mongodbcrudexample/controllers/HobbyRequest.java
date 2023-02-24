package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.domain.Hobby;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
class HobbyRequest {

  private String name;
  private Integer frequency;

  public Hobby toEntity() {
    return Hobby.builder().name(this.name).frequency(this.frequency).build();
  }
}
