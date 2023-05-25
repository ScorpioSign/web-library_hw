package ru.skypro.homework.springboot.weblibrary_hw.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employees = List.of(
            new Employee("Катя", 90_000),
            new Employee("Дима", 102_000),
            new Employee("Олег", 80_000),
            new Employee("Вика", 165_000));

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }


}
