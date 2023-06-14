package ru.skypro.homework.springboot.weblibrary_hw.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    // метод возвращает полный список сотрудников
    @GetMapping()
    List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/salary/sum")
    public int sumSalary() {
        return employeeService.sumSalary();
    }

    @GetMapping("/salary/min")
    public EmployeeDTO minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/salary/max")
    public EmployeeDTO maxSalary() {
        return employeeService.maxSalary();

    }

    @GetMapping("/high-salary")
    public List<EmployeeDTO> salaryAboveAverage() {
        return employeeService.salaryAboveAverage();
    }

    // возвращает сотрудника по id
    @GetMapping("/{id}")
    @SneakyThrows
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO == null) {
            throw new IOException();
        }
        return employeeDTO;
    }


    @GetMapping("/salary/higher")
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(@RequestParam int salary) {
        return employeeService.getEmployeesWithSalaryHigherThan(salary);
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public void deleteEmployee(@PathVariable int id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new IOException();
        }
        employeeService.deleteEmployee(id);
    }

    // метод дабавляет сотрудника в базу
    @PostMapping("/")
    @SneakyThrows
    public void addEmployee(@RequestBody EmployeeDTO employee) {
        if (employee.getId() == 0 || employee.getId() < getAllEmployees().size()) {
            throw new IOException();
        }
        employeeService.addEmployee(employee);
    }

    // метод для изменения данных сотрудника
    @PutMapping("/{id}")
    @SneakyThrows
    public void editEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO existingEmployeeDTO = employeeService.getEmployeeById(id);
        if (existingEmployeeDTO == null) {
            throw new IOException();
        }
        if (employeeDTO.getId() == 0 || employeeDTO.getId() < getAllEmployees().size()) {
            throw new IOException();
        }
        employeeService.editEmployee(id, employeeDTO);
    }

    // метод возвращает список сотрудников по позиции
    @GetMapping("/position/{position}")
    public List<EmployeeDTO> getEmployeeByPosition(@RequestParam("position") String position) {
        return employeeService.getEmployeeByPositionName(position);
    }

    // возвращает сотрудника по id с должностью
    @GetMapping("/{id}/full_info")
    public EmployeeFullInfo getAllEmployeeByIdFullInfo(@PathVariable Integer id) throws IncorrectIdException {
        return employeeService.getAllEmployeeByIdFullInfo(id);
    }

    // метод возвращает информацию о сотрудниках, основываясь на номере страницы.
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") Integer page) {
        return employeeService.getEmployeeFromPage(page);
    }

    // возвращает всех сотрудников с должностями
    @GetMapping("/full_info")
    List<EmployeeFullInfo> getAllEmployeeFullInfo() {
        return employeeService.getAllEmployeeFullInfo();
    }


}
