package br.com.valdemarjr.springboot_read_replicas_example.domain;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Product(@Id Long id, String name, BigDecimal price) {}
