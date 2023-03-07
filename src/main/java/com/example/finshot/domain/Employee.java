package com.example.finshot.domain;

import com.example.finshot.api.request.EmployeeUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeePosition position;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Builder
    public Employee(Long id, EmployeePosition position, String name, String phone, String email) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void update(EmployeeUpdateDto employeeUpdateDto) {
        this.id = employeeUpdateDto.getId();
        this.email = employeeUpdateDto.getEmail();
        this.name = employeeUpdateDto.getName();
        this.position = employeeUpdateDto.getPosition();
        this.phone = employeeUpdateDto.getPhone();
    }
}
