package com.example.finshot.domain;

import com.example.finshot.api.request.EmployeeUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "numeric_sequence")
    @GenericGenerator(name = "numeric_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "numeric_sequence"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "number_format", value = "%03d")
    })
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Builder
    public Employee(String id, EmployeePosition position, String name, String phone, String email) {
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
