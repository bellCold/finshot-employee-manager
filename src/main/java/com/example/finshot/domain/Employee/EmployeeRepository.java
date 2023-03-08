package com.example.finshot.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    List<Employee> findAllByOrderByName();

    List<Employee> findByPositionContaining(EmployeePosition position);

    List<Employee> findByNameContaining(String searchWord);

    List<Employee> findByPhoneContaining(String searchWord);

    List<Employee> findByEmailContaining(String searchWord);
}
