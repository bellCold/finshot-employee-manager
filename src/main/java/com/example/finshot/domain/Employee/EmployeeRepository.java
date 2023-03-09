package com.example.finshot.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    List<Employee> findAllByOrderByName();

    List<Employee> findByNameContaining(String searchWord);

    List<Employee> findByPhoneContaining(String searchWord);

    List<Employee> findByEmailContaining(String searchWord);

    List<Employee> findByPosition(EmployeePosition position);
}
