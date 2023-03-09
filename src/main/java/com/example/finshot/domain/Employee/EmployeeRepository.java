package com.example.finshot.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findEmployee(Long id);

    List<Employee> findEmployeeByPosition(EmployeePosition position);

    List<Employee> findByName(String name);
}
