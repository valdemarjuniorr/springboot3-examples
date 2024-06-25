package br.com.valdemarjr.modulith_example.service.confirm_order;

import br.com.valdemarjr.modulith_example.service.order.OrderPlacedEvent;
import java.util.logging.Logger;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
class OrderConfirmationService {

  private final Logger log = Logger.getLogger(OrderConfirmationService.class.getName());

  private final OrderConfirmationRepository repository;

  /* This Validation class can't be accessed from outside it modules */
  //  private final Validation validation;
  //  ProductService(ProductRepository repository, Validation validation) {
  //    this.repository = repository;
  //    this.validation = validation;
  //  }

  OrderConfirmationService(OrderConfirmationRepository repository) {
    this.repository = repository;
  }

  @ApplicationModuleListener
  void on(OrderPlacedEvent order) {
    log.info("Order placed: " + order.orderId());
    repository.save(new OrderConfirmation(null, order.orderId()));
  }
}
