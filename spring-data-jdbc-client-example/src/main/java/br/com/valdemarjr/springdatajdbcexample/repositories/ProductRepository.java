package br.com.valdemarjr.springdatajdbcexample.repositories;

import br.com.valdemarjr.springdatajdbcexample.domain.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private final JdbcClient jdbcClient;

  public ProductRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public Optional<Product> findBy(Long id) {
    return jdbcClient
        .sql("SELECT id, description FROM product WHERE id = :id")
        .param("id", id)
        .query(Product.class)
        .optional();
  }

  public List<Product> findAll() {
    return this.jdbcClient.sql("SELECT id, description FROM product").query(Product.class).list();
  }

  public Integer create(Product product) {
    return jdbcClient
        .sql("INSERT INTO product (id, description) VALUES (:id, :description)")
        .param("id", product.id())
        .param("description", product.description())
        .update();
  }

  public Integer delete(Long id) {
    return jdbcClient.sql("DELETE FROM product WHERE id = :id").param("id", id).update();
  }

  public Integer update(Product product) {
    return jdbcClient
        .sql("UPDATE product SET description = :description WHERE id = :id")
        .param("description", product.description())
        .param("id", product.id())
        .update();
  }
}
