package br.com.valdemarjr.springboot_read_replicas_example.repository;

import br.com.valdemarjr.springboot_read_replicas_example.domain.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {}
