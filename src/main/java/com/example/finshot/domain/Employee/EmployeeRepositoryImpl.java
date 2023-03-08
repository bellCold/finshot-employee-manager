package com.example.finshot.domain.Employee;

import com.example.finshot.api.request.EmployeeSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.finshot.domain.Employee.QEmployee.employee;


@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final JPQLQueryFactory queryFactory;


    @Override
    public List<Employee> findEmployee(EmployeeSearchRequestDto employeeSearchRequestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.hasText(String.valueOf(employeeSearchRequestDto.getId()))) {
            builder.and(employee.id.eq(employeeSearchRequestDto.getId()));
        }
        if (!StringUtils.hasText(employeeSearchRequestDto.getPhone())) {
            builder.and(employee.phone.eq(employeeSearchRequestDto.getPhone()));
        }
        if (!StringUtils.hasText(employeeSearchRequestDto.getName())) {
            builder.and(employee.name.eq(employeeSearchRequestDto.getName()));
        }
        if (!StringUtils.hasText(employeeSearchRequestDto.getEmail())) {
            builder.and(employee.email.eq(employeeSearchRequestDto.getEmail()));
        }

        if (!StringUtils.hasText(employeeSearchRequestDto.getPosition().toString())) {
            builder.and(employee.position.eq(employeeSearchRequestDto.getPosition()));
        }

        return queryFactory.selectFrom(employee).where(builder).fetch();
    }

}
