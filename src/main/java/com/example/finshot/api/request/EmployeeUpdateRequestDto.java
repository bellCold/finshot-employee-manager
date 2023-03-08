package com.example.finshot.api.request;

import com.example.finshot.domain.EmployeePosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdateRequestDto {
    private Long id;
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phone;

    @Email
    private String email;

    private EmployeePosition position;
}
