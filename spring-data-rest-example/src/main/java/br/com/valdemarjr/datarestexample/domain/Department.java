package br.com.valdemarjr.datarestexample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("DEPARTMENTS")
public record Department(@Id Long id, String name) {

  public Department(String name) {
    this(null, name);
  }
}
