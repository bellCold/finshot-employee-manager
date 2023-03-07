package com.example.finshot.api.response;

import com.example.finshot.domain.Employee;
import lombok.Getter;

import java.util.List;

@Getter
public class EmployeeSearchResponseDto {
    List<Employee> employees;
}
