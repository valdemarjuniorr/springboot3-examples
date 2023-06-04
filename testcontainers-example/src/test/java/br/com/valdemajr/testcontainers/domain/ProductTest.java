package br.com.valdemajr.testcontainers.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    private static final String PRODUCT_NAME = "product name";

    @Test
    void constructor() {
        var product = new Product(PRODUCT_NAME);
        assertThat(product.getName()).isEqualTo(PRODUCT_NAME);
        assertThat(product.getId()).isNull();
    }
}
