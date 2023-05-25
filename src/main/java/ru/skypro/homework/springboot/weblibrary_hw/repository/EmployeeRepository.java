package ru.skypro.homework.springboot.weblibrary_hw.repository;

import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {


    public List<Employee> getAllEmployees();
}
