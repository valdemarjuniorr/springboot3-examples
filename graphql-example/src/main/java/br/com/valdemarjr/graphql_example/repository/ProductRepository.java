package br.com.valdemarjr.graphql_example.repository;

import br.com.valdemarjr.graphql_example.controllers.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {}
