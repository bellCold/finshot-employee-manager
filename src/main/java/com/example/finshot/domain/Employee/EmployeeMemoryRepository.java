package com.example.finshot.domain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeMemoryRepository implements EmployeeRepository {

    private final Map<Long, Employee> memoryRepository = new HashMap<>();
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
        for (Long id : memoryRepository.keySet()) {
            if (memoryRepository.get(id).getPosition() == position) {
                employees.add(memoryRepository.get(id));
            }
        }
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        for (Long id : memoryRepository.keySet()) {
            if (memoryRepository.get(id).getName().equals(name)) {
                employees.add(memoryRepository.get(id));
            }
        }
        return employees;
    }
}
