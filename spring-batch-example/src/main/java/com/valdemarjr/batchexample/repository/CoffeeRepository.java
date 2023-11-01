package com.valdemarjr.batchexample.repository;


import com.valdemarjr.batchexample.domain.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {}
