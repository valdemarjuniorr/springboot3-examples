package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrderController {

  private final OrderService service;

  OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping
  void place(@RequestBody Order order) {
    service.place(order);
  }
}
