package ru.skypro.homework.springboot.weblibrary_hw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public int sumSalary() {
        return employeeService.sumSalary();
    }

    @GetMapping("/salary/min")
    public Employee minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/salary/max")
    public Employee maxSalary() {
        return employeeService.maxSalary();

    }

    @GetMapping("/high-salary")
    public List<Employee> salaryAboveAverage() {
        return employeeService.salaryAboveAverage();
    }


}
