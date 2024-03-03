package br.com.valdemar.kafkaexample.controllers;

import br.com.valdemar.kafkaexample.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
	public void sendMessage(@RequestBody ProductMessage productMessage) {
		service.sendMessage(productMessage.toEntity());
	}
}
