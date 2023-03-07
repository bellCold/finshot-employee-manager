package com.example.finshot.domain;

import com.example.finshot.api.request.EmployeeSearchRequestDto;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findEmployee(EmployeeSearchRequestDto employeeSearchRequestDto);
}
