package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.api.request.EmployeeSearchRequestDto;
import com.example.finshot.api.request.EmployeeUpdateRequestDto;
import com.example.finshot.api.response.EmployeeListResponseDto;
import com.example.finshot.api.response.EmployeeSearchResponseDto;
import com.example.finshot.api.response.EmployeeUpdateResponseDto;
import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeeRepository;
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
        List<Employee> employees = employeeRepository.findAllByOrderByName();
        EmployeeListResponseDto employeeListResponseDto = new EmployeeListResponseDto();
        toEmployeeListResponseDto(employees, employeeListResponseDto.getEmployees());
        return employeeListResponseDto.getEmployees();
    }

    public EmployeeSearchResponseDto findByField(EmployeeSearchRequestDto employeeSearchRequestDto) {
        List<Employee> employees = employeeRepository.findEmployee(employeeSearchRequestDto);
        EmployeeSearchResponseDto employeeSearchResponseDto = new EmployeeSearchResponseDto();
        toEmployeeListResponseDto(employees, employeeSearchResponseDto.getEmployees());
        return employeeSearchResponseDto;
    }

    private void toEmployeeListResponseDto(List<Employee> employees, List<Employee> responseDto) {
        responseDto.addAll(employees);
    }

    public void deleteEmployee(Long path) {
        Employee employee = employeeRepository.findById(path).orElseThrow(RuntimeException::new);
        employeeRepository.delete(employee);
    }

    @Transactional
    public void updateEmployee(Long path, EmployeeUpdateRequestDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(path).orElseThrow(RuntimeException::new);
        employee.update(employeeUpdateDto);
    }

    public EmployeeUpdateResponseDto findEmployee(Long path) {
        Employee employee = employeeRepository.findById(path).orElseThrow(RuntimeException::new);
        return EmployeeUpdateResponseDto.toDto(employee);
    }
}
