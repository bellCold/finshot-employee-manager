package com.example.finshot.employee;

import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeePosition;
import com.example.finshot.domain.Employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FindEmployeeTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void registerEmployee() {
        Employee employee = Employee.builder()
                .name("김종찬")
                .email("test@naver.com")
                .phone("010-1234-1234")
                .position(EmployeePosition.CEO)
                .build();

        employeeRepository.save(employee);
    }


    @Test
    @DisplayName("enum값으로 회원찾기")
    void findEmployeeByEnumType() {
        List<Employee> all = employeeRepository.findAll();

    }
}
