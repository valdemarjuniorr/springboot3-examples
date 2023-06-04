package br.com.valdemajr.testcontainers.repository;

import br.com.valdemajr.testcontainers.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
