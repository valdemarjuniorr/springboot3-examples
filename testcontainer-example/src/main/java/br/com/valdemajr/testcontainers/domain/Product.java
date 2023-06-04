package br.com.valdemajr.testcontainers.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
	@SequenceGenerator(name = "product_gen", sequenceName = "product_seq", allocationSize = 1)
	private Long id;

	private String name;

	public Product(String name) {
		this.name = name;
	}
}
