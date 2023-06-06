package ru.skypro.homework.springboot.weblibrary_hw.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/")
    List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
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


    @GetMapping("/{id}")
    @SneakyThrows
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new IOException();
        }
        return employee;
    }


    @GetMapping("/salary/higher")
    public List<Employee> getEmployeesWithSalaryHigherThan(@RequestParam int salary) {
        return employeeService.getEmployeesWithSalaryHigherThan(salary);
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public void deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new IOException();
        }
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/")
    @SneakyThrows
    public void addEmployee(@RequestBody Employee employee) {
        if (employee.getId() == 0 || employee.getId() < getAllEmployees().size()) {
            throw new IOException();
        }
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public void editEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee == null) {
            throw new IOException();
        }
        if (employee.getId() == 0 || employee.getId() < getAllEmployees().size()) {
            throw new IOException();
        }
        employeeService.editEmployee(id, employee);
    }


}
