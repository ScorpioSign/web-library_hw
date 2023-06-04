package ru.skypro.homework.springboot.weblibrary_hw.service;

import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    public int sumSalary();

    public Employee minSalary();

    public Employee maxSalary();

    public List<Employee> salaryAboveAverage();
// прописать 4 метода
}
