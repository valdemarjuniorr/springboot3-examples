package br.com.valdemarjr.springdatajdbcexample.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.valdemarjr.springdatajdbcexample.domain.Product;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.simple.JdbcClient;

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

  @Test
  void findAll() {
    var productMock = new Product(ID, DESCRIPTION);
    var statementMock = mock(JdbcClient.StatementSpec.class);
    var mappedQuery = mock(JdbcClient.MappedQuerySpec.class);

    when(jdbcClient.sql(anyString())).thenReturn(statementMock);
    when(statementMock.query(Product.class)).thenReturn(mappedQuery);
    when(mappedQuery.list()).thenReturn(List.of(productMock));

    var products = productRepository.findAll();

    assertThat(products).isNotNull().isNotEmpty().hasSize(1).contains(productMock);
  }

  @Test
  void create() {
    var productMock = new Product(ID, DESCRIPTION);
    var statementMock = mock(JdbcClient.StatementSpec.class);

    when(jdbcClient.sql(anyString())).thenReturn(statementMock);
    when(statementMock.param("id", ID)).thenReturn(statementMock);
    when(statementMock.param("description", DESCRIPTION)).thenReturn(statementMock);
    when(statementMock.update()).thenReturn(1);

    var result = productRepository.create(productMock);

    assertThat(result).isNotNull().isEqualTo(1);
  }

  @Test
  void delete() {
    var statementMock = mock(JdbcClient.StatementSpec.class);

    when(jdbcClient.sql(anyString())).thenReturn(statementMock);
    when(statementMock.param("id", ID)).thenReturn(statementMock);
    when(statementMock.update()).thenReturn(1);

    var result = productRepository.delete(ID);

    assertThat(result).isNotNull().isEqualTo(1);
  }

  @Test
  void update() {
    var productMock = new Product(ID, DESCRIPTION);
    var statementMock = mock(JdbcClient.StatementSpec.class);

    when(jdbcClient.sql(anyString())).thenReturn(statementMock);
    when(statementMock.param("description", DESCRIPTION)).thenReturn(statementMock);
    when(statementMock.param("id", ID)).thenReturn(statementMock);
    when(statementMock.update()).thenReturn(1);

    var result = productRepository.update(productMock);

    assertThat(result).isNotNull().isEqualTo(1);
  }
}
