package br.com.valdemarjr.datarestexample.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.valdemarjr.datarestexample.domain.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
