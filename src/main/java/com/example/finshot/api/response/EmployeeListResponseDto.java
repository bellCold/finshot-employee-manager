package com.example.finshot.api.response;

import com.example.finshot.domain.Employee;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployeeListResponseDto {
    List<Employee> employees = new ArrayList<>();
}
