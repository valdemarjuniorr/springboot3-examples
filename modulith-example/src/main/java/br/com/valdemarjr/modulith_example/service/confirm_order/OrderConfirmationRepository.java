package br.com.valdemarjr.modulith_example.service.confirm_order;

import org.springframework.data.repository.ListCrudRepository;

interface OrderConfirmationRepository extends ListCrudRepository<OrderConfirmation, Long> {}
