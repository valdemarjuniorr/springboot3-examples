package br.com.valdemarjr.datarestexample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.valdemarjr.datarestexample.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
}
