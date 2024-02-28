package br.com.valdemarjr.springdatajdbcexample.repositories;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.valdemarjr.springdatajdbcexample.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

  private static final long ID = 1L;
  private static final String DESCRIPTION = "Product 1";

  @Mock private JdbcClient jdbcClient;
  @InjectMocks private ProductRepository productRepository;

  @Test
  void findById() {
    var productMock = new Product(ID, DESCRIPTION);
    var statementMock = mock(JdbcClient.StatementSpec.class);
    var mappedQuery = mock(JdbcClient.MappedQuerySpec.class);

    when(jdbcClient.sql(anyString())).thenReturn(statementMock);
    when(statementMock.param("id", ID)).thenReturn(statementMock);
    when(statementMock.query(Product.class)).thenReturn(mappedQuery);
    when(mappedQuery.optional()).thenReturn(Optional.of(productMock));

    var product = productRepository.findBy(ID);

    assertThat(product).isNotNull().hasValue(productMock);
  }
}
