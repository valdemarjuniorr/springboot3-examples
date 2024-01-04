package br.com.valdemarjr.contractstubrunnerexample.controllers;

import br.com.valdemarjr.contractstubrunnerexample.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

	@GetMapping
	public List<Product> get() {
		log.info("getting products");
		return null;
	}

}
