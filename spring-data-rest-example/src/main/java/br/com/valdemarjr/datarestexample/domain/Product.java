package br.com.valdemarjr.datarestexample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCTS")
public record Product(@Id Long id, String name, AggregateReference<Department, Long> departmentId, Double price) {
}
