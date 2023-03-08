package com.example.finshot.api.response;

import com.example.finshot.domain.Employee.Employee;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployeeSearchResponseDto {
    List<Employee> employees = new ArrayList<>();
}
