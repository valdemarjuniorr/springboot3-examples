package br.com.valdemarjr.datarestexample.repository;

import br.com.valdemarjr.datarestexample.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
