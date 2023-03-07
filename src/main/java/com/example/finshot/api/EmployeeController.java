package com.example.finshot.api;

import com.example.finshot.api.request.EmployeeRegisterRequestDto;
import com.example.finshot.application.EmployeeService;
import com.example.finshot.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/employee/search";
    }

//    @GetMapping("/update/{path}")
//    public String updateForm(@PathVariable String path, Model model) {
//        Employee employee = employeeService.findEmployee(path);
//        model.addAttribute("employee", employee);
//        return "/employee/update";
//    }
//
//    @PutMapping("/update/{path}")
//    public String updateEmployee(@PathVariable String path, @Valid EmployeeUpdateDto employeeUpdateDto) {
//        employeeService.update(path, employeeUpdateDto);
//        return "redirect:/";
//    }
//
//    @DeleteMapping("/delete/{path}")
//    public String deleteEmployee(@PathVariable String path) {
//        employeeService.delete(path);
//        return "redirect:/";
//    }
}
