package com.example.finshot.employee;

import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeePosition;
import com.example.finshot.domain.Employee.EmployeeJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindEmployeeTest2 {

    @Autowired
    EmployeeJpaRepository employeeRepository;

    @Test
    @DisplayName("enumType으로 찾기")
    void findEnumType() {
        List<Employee> byPositionContaining = employeeRepository.findByPosition(EmployeePosition.CEO);
        assertThat(byPositionContaining).isNotNull();
    }
}
