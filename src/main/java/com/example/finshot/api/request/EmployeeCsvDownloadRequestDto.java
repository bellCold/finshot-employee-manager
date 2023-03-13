package com.example.finshot.api.request;

import com.example.finshot.domain.Employee.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeCsvDownloadRequestDto {
    private List<Employee> employees;
}
