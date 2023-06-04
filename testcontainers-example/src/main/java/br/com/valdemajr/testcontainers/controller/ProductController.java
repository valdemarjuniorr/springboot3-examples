package br.com.valdemajr.testcontainers.controller;

import br.com.valdemajr.testcontainers.domain.Product;
import br.com.valdemajr.testcontainers.exception.ProductNotFoundException;
import br.com.valdemajr.testcontainers.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
@ResponseBody
public class ProductController {

	private final ProductService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public URI save(@RequestBody ProductRequest request) {
		log.info("Product request", request);
		var product = service.save(new Product(request.getName()));

		return UriComponentsBuilder.newInstance()
			.scheme("http")
			.host("localhost")
			.port("8080")
			.path("/product/{id}")
			.buildAndExpand(product.getId()).toUri();
	}

	@GetMapping("/{id}")
	public ProductResponse findBy(@PathVariable Long id) {
		log.info("Finding product with id {}", id);
		var product = service.findBy(id).orElseThrow(ProductNotFoundException::new);
		return new ProductResponse(product.getId(), product.getName());
	}
}
