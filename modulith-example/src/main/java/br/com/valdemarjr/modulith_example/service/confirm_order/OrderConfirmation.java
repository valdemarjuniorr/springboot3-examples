package br.com.valdemarjr.modulith_example.service.confirm_order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "orders_confirmation")
record OrderConfirmation(@Id Long id, Long orderId) {}
