package br.com.valdemajr.testcontainers.service;

import br.com.valdemajr.testcontainers.domain.Product;
import br.com.valdemajr.testcontainers.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository repository;

	@InjectMocks
	private ProductService service;

	@Test
	void save() {
		var productMock = mock(Product.class);
		when(repository.save(productMock)).thenReturn(productMock);

		var saved = service.save(productMock);

		assertThat(saved).isEqualTo(productMock);
		verify(repository).save(productMock);
	}
}
