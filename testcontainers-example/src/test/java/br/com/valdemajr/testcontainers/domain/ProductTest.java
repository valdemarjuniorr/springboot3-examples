package br.com.valdemajr.testcontainers.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

	@Test
	void constructor() {
		var product = new Product("product name");
		assertThat(product.getName()).isEqualTo("product name");
		assertThat(product.getId()).isNull();
	}
}
