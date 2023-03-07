package com.example.finshot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    List<Employee> findAllByOrderByName();
}
