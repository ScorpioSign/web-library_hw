package ru.skypro.homework.springboot.weblibrary_hw.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Map<Integer, Employee> employeeMap = new HashMap<>();{
        employeeMap.put(1,new Employee(1,"Катя", 90_000));
        employeeMap.put(2,new Employee(2,"Дима", 102_000));
        employeeMap.put(3,new Employee(3,"Олег", 80_000));
        employeeMap.put(4,new Employee(4,"Вика", 165_000));
    }


    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Employee getEmployeeById(int id) {   //+
        return employeeMap.get(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getId(),employee);

    }

    @Override
    public void editEmployee(int id, Employee employee) {
        employeeMap.remove(employee.getId());
        employeeMap.put(employee.getId(),employee);


    }

    @Override
    public void deleteEmployee(int id) { //+
        employeeMap.remove(id);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryHigherThan(int salary) {
        List<Employee> collect = employeeMap.values().stream()
                .filter(t -> t.getSalary() > salary)
                .collect(Collectors.toList());
        return collect;
    }
}
