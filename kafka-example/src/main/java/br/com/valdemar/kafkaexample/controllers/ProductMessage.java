package br.com.valdemar.kafkaexample.controllers;

import br.com.valdemar.kafkaexample.domain.Product;

record ProductMessage(Long id, String description) {
  public Product toEntity() {
    return new Product(id, description);
  }
}
