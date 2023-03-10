package com.example.finshot.api;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.api.request.EmployeeUpdateRequestDto;
import com.example.finshot.api.response.EmployeeUpdateResponseDto;
import com.example.finshot.application.EmployeeService;
import com.example.finshot.domain.Employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/register")
    public String registerForm() {
        return "employee/register";
    }

    @PostMapping("/register")
    public String registerEmployee(@Valid EmployeeRegisterRequestDto employeeRegisterDto) {
        employeeService.register(employeeRegisterDto);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchEmployee(@RequestParam String searchWord, Model model) {
        List<Employee> employees = employeeService.findBySearchWord(searchWord);
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/update/{path}")
    public String updateForm(@PathVariable Long path, Model model) {
        EmployeeUpdateResponseDto employee = employeeService.findEmployee(path);
        model.addAttribute("employee", employee);
        return "/employee/update";
    }

    @PutMapping("/update/{path}")
    public String updateEmployee(@PathVariable Long path, @Valid EmployeeUpdateRequestDto employeeUpdateDto) {
        employeeService.updateEmployee(path, employeeUpdateDto);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{path}")
    public String deleteEmployee(@PathVariable Long path) {
        employeeService.deleteEmployee(path);
        return "redirect:/";
    }
}
