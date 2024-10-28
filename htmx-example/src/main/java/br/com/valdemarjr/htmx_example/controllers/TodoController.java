package br.com.valdemarjr.htmx_example.controllers;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** It must use @Controller annotation instead of @RestController */
@Controller
@RequestMapping("/todos")
class TodoController {

  private final Set<Todo> todos = new ConcurrentSkipListSet<>(Comparator.comparingInt(Todo::id));

  TodoController() {
    this.todos.add(Todos.todo("Learn HTMX"));
    this.todos.add(Todos.todo("Learn Thyemeleaf"));
    this.todos.add(Todos.todo("Go to the gym"));
  }

  @GetMapping
  String todos(Model model) {
    model.addAttribute("todos", this.todos);
    return "todos";
  }

  /**
   * It is necessary to use @ResponseBody annotation. In this case, the delete method keeps in the
   * same view; it is not necessary to return a new view name.
   */
  @ResponseBody
  @DeleteMapping("/{id}")
  void delete(@PathVariable Integer id) {
    this.todos.removeIf(todo -> todo.id().equals(id));
  }

  @PostMapping
  HtmxResponse add(@RequestParam("new-todo") String newTodo, Model model) {
    this.todos.add(Todos.todo(newTodo));
    model.addAttribute("todos", this.todos);

    return HtmxResponse.builder().view("todos :: todos").view("todos :: todos-form").build();
  }
}
