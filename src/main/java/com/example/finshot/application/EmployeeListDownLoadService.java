package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeCsvDownloadRequestDto;
import com.example.finshot.domain.Employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static com.example.finshot.global.constants.FinShotConstant.*;

@Service
@RequiredArgsConstructor
public class EmployeeListDownLoadService {

    private final EmployeeService employeeService;

    public void downloadCsv(HttpServletResponse response, EmployeeCsvDownloadRequestDto requestDto) throws IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"employeeList.csv\"");

        Writer writer = new OutputStreamWriter(response.getOutputStream());

        List<Employee> employees = requestDto.getEmployees();

        makeCsvForm(employees, writer);
        writer.flush();
    }

    private void makeCsvForm(List<Employee> employees, Writer writer) throws IOException {
        writer.append(CSV_HEADER).append(LF);

        for (Employee employee : employees) {
            writer.append(String.valueOf(employee.getId())).append(COMMA);
            writer.append(employee.getPosition().name()).append(COMMA);
            writer.append(employee.getName()).append(COMMA);
            writer.append(employee.getPhone()).append(COMMA);
            writer.append(employee.getEmail()).append(COMMA);
            writer.append(LF);
        }
    }

    public File makeEmployeeListCsvFile() throws IOException {
        List<Employee> employees = employeeService.findAll();
        String fileName = "mydata.csv";
        FileWriter fileWriter = new FileWriter(fileName);
        makeCsvForm(employees, fileWriter);

        return null;
    }
}
