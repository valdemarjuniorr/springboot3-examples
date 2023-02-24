package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.domain.Hobby;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class HobbyResponse {

  private String name;
  private Integer frequency;

  public HobbyResponse(Hobby hobby) {
    this.name = hobby.getName();
    this.frequency = hobby.getFrequency();
  }
}
