package br.com.valdemarjr.mongodbcrudexample.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Hobby {

  private String name;
  private Integer frequency;
}
