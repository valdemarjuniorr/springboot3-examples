package br.com.valdemarjr.mongodbcrudexample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@Document
public class User {

  @Id
  private String id;

  private String name;

  private Integer age;

  private List<Hobby> hobbies;
}
