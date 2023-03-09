package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.api.request.EmployeeUpdateRequestDto;
import com.example.finshot.api.response.EmployeeListResponseDto;
import com.example.finshot.api.response.EmployeeUpdateResponseDto;
import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeeJpaRepository;
import com.example.finshot.domain.Employee.EmployeePosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeJpaRepository employeeRepository;

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
        if (isNumeric(searchWord) && isExistEmployeeById(searchWord)) {
            Employee employee = employeeRepository.findById(Long.parseLong(searchWord)).orElseThrow();
            employeeListResponseDto.getEmployees().add(employee);
        }

        return toEmployeeListResponseDto(employeeListResponseDto, searchWord);
    }

    private boolean isExistEmployeeById(String searchWord) {
        return employeeRepository.existsById(Long.parseLong(searchWord));
    }

    private List<Employee> toEmployeeListResponseDto(EmployeeListResponseDto employeeListResponseDto, String searchWord) {
        List<String> positionList = EmployeePosition.getPosition(searchWord);

        if (positionList != null) {
            positionList.forEach(position -> {
                List<Employee> byPosition = employeeRepository.findByPosition(EmployeePosition.valueOf(position));
                toEmployeeListResponseDto(byPosition, employeeListResponseDto.getEmployees());
            });
        }

        List<Employee> byEmailContaining = employeeRepository.findByEmailContaining(searchWord);
        List<Employee> byNameContaining = employeeRepository.findByNameContaining(searchWord);
        List<Employee> byPhoneContaining = employeeRepository.findByPhoneContaining(searchWord);

        toEmployeeListResponseDto(byEmailContaining, employeeListResponseDto.getEmployees());
        toEmployeeListResponseDto(byPhoneContaining, employeeListResponseDto.getEmployees());
        toEmployeeListResponseDto(byNameContaining, employeeListResponseDto.getEmployees());

        sortByName(employeeListResponseDto);

        return distinct(employeeListResponseDto);
    }

    private List<Employee> distinct(EmployeeListResponseDto employeeListResponseDto) {
        return employeeListResponseDto.getEmployees().stream().distinct().collect(Collectors.toList());
    }

    private void sortByName(EmployeeListResponseDto employeeListResponseDto) {
        employeeListResponseDto.getEmployees().sort(Comparator.comparing(Employee::getName));
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
