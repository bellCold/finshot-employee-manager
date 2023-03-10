package com.example.finshot.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findById(Long id);

    List<Employee> findByPosition(EmployeePosition position);

    List<Employee> findByName(String name);
}
