package com.valdemarjr.batchexample.services;

import com.valdemarjr.batchexample.domain.Coffee;
import com.valdemarjr.batchexample.repository.CoffeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoffeeService {

  private final CoffeeRepository repository;

  public void save(List<Coffee> coffees) {
    log.info("Saving coffees size: {}", coffees.size());
    repository.saveAll(coffees);
    log.info("Coffees saved");
  }
}
