package com.example.finshot.domain;

import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeeMemoryRepository;
import com.example.finshot.domain.Employee.EmployeePosition;
import com.example.finshot.domain.Employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    EmployeeRepository repository = new EmployeeMemoryRepository();

    @BeforeEach
    void registerEmployee() {
        Employee employee = Employee.builder()
                .name("김종찬")
                .email("test@naver.com")
                .phone("010-1234-1234")
                .position(EmployeePosition.CEO)
                .build();

        repository.save(employee);
    }


    @Test
    @DisplayName("name값으로 회원찾기")
    void findEmployeeByName() {
        List<Employee> employees = repository.findByName("김종찬");
        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("enum값으로 회원찾기")
    void findEmployeeByEnum() {
        List<Employee> employees = repository.findByPosition(EmployeePosition.CEO);
        assertThat(employees.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("id 값으로 회원찾기")
    void findByIdTest() {
        Employee employee = repository.findById(1L);
        assertThat(employee.getEmail()).isEqualTo("test@naver.com");
        assertThat(employee.getPhone()).isEqualTo("010-1234-1234");
    }
}
