package br.com.valdemajr.testcontainers.service;

import br.com.valdemajr.testcontainers.domain.Product;
import br.com.valdemajr.testcontainers.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product save(Product product) {
        log.info("Saving product {}", product);
        var saved = repository.save(product);
        log.info("Saved");
        return saved;
    }

    public Optional<Product> findBy(Long id) {
        return repository.findById(id);
    }
}

