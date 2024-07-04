package br.com.valdemarjr.graphql_example.controllers;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Product(@Id Long id, String name, BigDecimal price) {}
