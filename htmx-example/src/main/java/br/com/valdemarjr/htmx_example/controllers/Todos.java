package br.com.valdemarjr.htmx_example.controllers;

import java.util.concurrent.atomic.AtomicInteger;

class Todos {

  private static final AtomicInteger id = new AtomicInteger(0);

  static Todo todo(String description) {
    return new Todo(id.incrementAndGet(), description);
  }
}
