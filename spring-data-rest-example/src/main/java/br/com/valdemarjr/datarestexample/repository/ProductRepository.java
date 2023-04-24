package br.com.valdemarjr.datarestexample.repository;

import br.com.valdemarjr.datarestexample.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
