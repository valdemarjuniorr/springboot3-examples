package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "orders")
record Order(@Id Long id, Integer quantity, Long productId) {}
