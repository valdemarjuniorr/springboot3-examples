package br.com.valdemarjr.graphql_example.controllers;

import br.com.valdemarjr.graphql_example.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest
class ProductControllerTest {

  private static final long ID = 1L;
  private static final String NAME = "Product 1";
  private static final BigDecimal PRICE = BigDecimal.valueOf(10.0);

  @Autowired private GraphQlTester tester;
  @MockBean private ProductService service;

  @Test
  void findAll() {
    Mockito.when(service.findAll()).thenReturn(List.of(getProduct()));
    tester
        .document("query { findAll { id name price } }")
        .execute()
        .path("data.findAll")
        .entityList(Product.class)
        .hasSize(1);

    Mockito.verify(service).findAll();
  }

  @Test
  void findById() {
    Mockito.when(service.findBy(ID)).thenReturn(getProduct());
    tester
        .document("query { findById(id: 1) { id name price } }")
        .execute()
        .path("data.findById")
        .entity(Product.class)
        .isEqualTo(getProduct()); // GraphQL returns a float

    Mockito.verify(service).findBy(ID);
  }

  @Test
  void save() {
    Mockito.when(service.save(NAME, PRICE)).thenReturn(getProduct());
    tester
        .document("mutation { save(name: \"Product 1\", price: 10) { id name price } }")
        .execute()
        .path("data.save")
        .entity(Product.class)
        .isEqualTo(getProduct()); // GraphQL returns a float

    Mockito.verify(service).save(NAME, PRICE);
  }

  @Test
  void delete() {
    tester
        .document("mutation { delete(id: 1) }")
        .execute()
        .path("data.delete")
        .entity(Long.class)
        .isEqualTo(ID);

    Mockito.verify(service).delete(ID);
  }

  private static Product getProduct() {
    return new Product(ID, NAME, PRICE);
  }
}
