package com.example.finshot.api;

import com.example.finshot.application.EmployeeService;
import com.example.finshot.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public String employeeList(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }
}
