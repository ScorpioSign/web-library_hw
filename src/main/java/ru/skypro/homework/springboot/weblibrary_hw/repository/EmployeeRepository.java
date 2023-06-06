package ru.skypro.homework.springboot.weblibrary_hw.repository;

import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {


    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void addEmployee(Employee employee);
    void editEmployee(int id, Employee employee);
    void deleteEmployee(int id);
    List<Employee> getEmployeesWithSalaryHigherThan(int salary);
}
