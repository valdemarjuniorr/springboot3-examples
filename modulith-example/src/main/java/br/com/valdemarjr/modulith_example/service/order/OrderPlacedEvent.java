package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.modulith.events.Externalized;

/**
 * This class represents an event published when an order is placed. This object is externalized to
 * the order queue.
 */
@Externalized(target = AmqpIntegrationConfiguration.ORDERS_Q)
public record OrderPlacedEvent(Long orderId) {}
