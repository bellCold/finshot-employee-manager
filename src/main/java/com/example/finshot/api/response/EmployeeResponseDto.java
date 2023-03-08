package com.example.finshot.api.response;

import com.example.finshot.domain.Employee.EmployeePosition;
import lombok.Getter;

@Getter
public class EmployeeResponseDto {
    private String id;
    private String email;
    private String phone;
    private String name;
    private EmployeePosition position;
}
