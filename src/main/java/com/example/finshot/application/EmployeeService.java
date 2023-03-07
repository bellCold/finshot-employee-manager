package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.api.request.EmployeeSearchRequestDto;
import com.example.finshot.api.request.EmployeeUpdateDto;
import com.example.finshot.api.response.EmployeeSearchResponseDto;
import com.example.finshot.domain.Employee;
import com.example.finshot.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void register(EmployeeRegisterRequestDto employeeRegisterDto) {
        Employee employee = employeeRegisterDto.toEntity();
        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public EmployeeSearchResponseDto findByField(EmployeeSearchRequestDto employeeSearchRequestDto) {
        List<Employee> employees = employeeRepository.findEmployee(employeeSearchRequestDto);
        EmployeeSearchResponseDto employeeSearchResponseDto = new EmployeeSearchResponseDto();
        toResponseDto(employees, employeeSearchResponseDto.getEmployees());
        return employeeSearchResponseDto;
    }

    private void toResponseDto(List<Employee> employees, List<Employee> responseDto) {
        responseDto.addAll(employees);
    }

    @Transactional
    public void update(String path, EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(path).orElseThrow(RuntimeException::new);
        employee.update(employeeUpdateDto);
    }

    @Transactional
    public void delete(String path) {
        Employee employee = employeeRepository.findById(path).orElseThrow(RuntimeException::new);
        employeeRepository.delete(employee);
    }

    public Employee findEmployee(String path) {
        return employeeRepository.findById(path).orElseThrow(RuntimeException::new);
    }
}
