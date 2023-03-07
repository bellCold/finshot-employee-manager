package com.example.finshot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

}
