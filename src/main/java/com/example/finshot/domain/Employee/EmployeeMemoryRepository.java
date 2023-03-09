package com.example.finshot.domain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeMemoryRepository implements EmployeeRepository {

    private static Map<Long, Employee> memoryRepository = new HashMap<>();
    private Long sequence = 1L;

    @Override
    public void save(Employee employee) {
        memoryRepository.put(sequence, employee);
        sequence++;
    }

    @Override
    public Employee findEmployee(Long id) {
        return memoryRepository.get(id);
    }

    @Override
    public List<Employee> findEmployeeByPosition(EmployeePosition position) {
        List<Employee> employees = new ArrayList<>();
        memoryRepository.forEach((id, employee) -> {
            if (employee.getPosition() == position) {
                employees.add(employee);
            }
        });
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        memoryRepository.forEach((id, employee) -> {
            if (employee.getName().equals(name)) {
                employees.add(employee);
            }
        });
        return employees;
    }
}
