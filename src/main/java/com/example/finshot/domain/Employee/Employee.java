package com.example.finshot.domain.Employee;

import com.example.finshot.api.request.EmployeeUpdateRequestDto;
import com.example.finshot.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends BaseEntity {

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

    public void update(EmployeeUpdateRequestDto employeeUpdateDto) {
        this.email = employeeUpdateDto.getEmail();
        this.name = employeeUpdateDto.getName();
        this.position = employeeUpdateDto.getPosition();
        this.phone = employeeUpdateDto.getPhone();
    }
}
