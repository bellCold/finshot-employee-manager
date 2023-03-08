package com.example.finshot.api.response;

import com.example.finshot.domain.Employee.Employee;
import com.example.finshot.domain.Employee.EmployeePosition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeUpdateResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private EmployeePosition position;

    @Builder
    public EmployeeUpdateResponseDto(Long id, String name, String phone, String email, EmployeePosition position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public static EmployeeUpdateResponseDto toDto(Employee employee) {
        return EmployeeUpdateResponseDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .name(employee.getName())
                .position(employee.getPosition())
                .phone(employee.getPhone())
                .build();
    }
}
