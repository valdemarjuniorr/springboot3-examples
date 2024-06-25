package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class OrderService {

  private final OrderRepository repository;
  private final ApplicationEventPublisher publisher;

  OrderService(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
    this.repository = orderRepository;
    this.publisher = publisher;
  }

  public void place(Order order) {
    var saved = repository.save(order);
    publisher.publishEvent(new OrderPlacedEvent(saved.id()));
  }
}
