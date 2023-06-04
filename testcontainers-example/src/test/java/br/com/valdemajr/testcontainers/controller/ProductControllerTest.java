package br.com.valdemajr.testcontainers.controller;

import br.com.valdemajr.testcontainers.domain.Product;
import br.com.valdemajr.testcontainers.exception.ProductNotFoundException;
import br.com.valdemajr.testcontainers.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private static final Long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "product name";
    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    @Test
    void save() {
        var productMock = mock(Product.class);
        var captor = ArgumentCaptor.forClass(Product.class);
        when(service.save(captor.capture())).thenReturn(productMock);
        when(productMock.getId()).thenReturn(PRODUCT_ID);

        var saved = controller.save(new ProductRequest(PRODUCT_NAME));

        assertThat(saved).hasToString("http://localhost:8080/product/1");
        verify(service).save(captor.getValue());
    }

    @Test
    void findByIdFound() {
        var productMock = mock(Product.class);
        when(service.findBy(PRODUCT_ID)).thenReturn(Optional.of(productMock));
        when(productMock.getId()).thenReturn(PRODUCT_ID);

        var found = controller.findBy(PRODUCT_ID);

        verify(service).findBy(PRODUCT_ID);
        assertThat(found).isNotNull();
    }

    @Test
    void findByIdNotFound() {
        when(service.findBy(PRODUCT_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.findBy(PRODUCT_ID))
                .isInstanceOf(ProductNotFoundException.class);

        verify(service).findBy(PRODUCT_ID);
    }
}
