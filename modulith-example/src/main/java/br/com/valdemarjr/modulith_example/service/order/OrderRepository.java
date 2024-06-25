package br.com.valdemarjr.modulith_example.service.order;

import org.springframework.data.repository.ListCrudRepository;

interface OrderRepository extends ListCrudRepository<Order, Long> {}
