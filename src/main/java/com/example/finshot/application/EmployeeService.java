package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.api.request.EmployeeUpdateRequestDto;
import com.example.finshot.api.response.EmployeeListResponseDto;
import com.example.finshot.api.response.EmployeeUpdateResponseDto;
import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeePosition;
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

    public List<Employee> findBySearchWord(String searchWord) {
        EmployeeListResponseDto employeeListResponseDto = new EmployeeListResponseDto();
        if (isNumeric(searchWord)) {
            Employee employee = employeeRepository.findById(Long.parseLong(searchWord)).orElseThrow(RuntimeException::new);
            employeeListResponseDto.getEmployees().add(employee);
        } else {
            addEmployeeBySearchWord(employeeListResponseDto, searchWord);
        }

        return employeeListResponseDto.getEmployees();
    }

    private void addEmployeeBySearchWord(EmployeeListResponseDto employeeListResponseDto, String searchWord) {
        List<Employee> byPositionContaining = employeeRepository.findByPositionContaining(EmployeePosition.valueOf(searchWord));
        List<Employee> byEmailContaining = employeeRepository.findByEmailContaining(searchWord);
        List<Employee> byNameContaining = employeeRepository.findByNameContaining(searchWord);
        List<Employee> byPhoneContaining = employeeRepository.findByPhoneContaining(searchWord);

        toEmployeeListResponseDto(byEmailContaining, employeeListResponseDto.getEmployees());
        toEmployeeListResponseDto(byPhoneContaining, employeeListResponseDto.getEmployees());
        toEmployeeListResponseDto(byNameContaining, employeeListResponseDto.getEmployees());
        toEmployeeListResponseDto(byPositionContaining, employeeListResponseDto.getEmployees());
    }

    private static boolean isNumeric(String searchWord) {
        return searchWord.matches("-?\\d+(\\.\\d+)?");  // 정규표현식으로 숫자인지 체크
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
