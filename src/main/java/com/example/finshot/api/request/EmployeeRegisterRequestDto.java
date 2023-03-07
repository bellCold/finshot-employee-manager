package com.example.finshot.api.request;

import com.example.finshot.domain.Employee;
import com.example.finshot.domain.EmployeePosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterRequestDto {
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phone;

    private EmployeePosition position;

    public Employee toEntity() {
        return Employee.builder()
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .position(this.position)
                .build();
    }
}
