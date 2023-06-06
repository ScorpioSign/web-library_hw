package ru.skypro.homework.springboot.weblibrary_hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.springboot.weblibrary_hw.pojo.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int sumSalary() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        int sum = 0;
        for (Employee employee : employees) {
            sum = sum + employee.getSalary();
        }
        return sum;
    }

    @Override
    public Employee minSalary() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        Employee employeeWithMinSalary = employees.get(0);
            for (Employee employee : employees) {
             if (employee.getSalary() < employeeWithMinSalary.getSalary()){
                 employeeWithMinSalary = employee;
             }

        }
        return employeeWithMinSalary;
    }

    @Override
    public Employee maxSalary() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        Employee employeeWithMaxSalary = employees.get(0);
        for (Employee employee : employees) {
            if (employee.getSalary() > employeeWithMaxSalary.getSalary()) {
                employeeWithMaxSalary = employee;
            }

        }
        return employeeWithMaxSalary;
    }

        @Override
        public List<Employee> salaryAboveAverage () {
            List<Employee> employees = employeeRepository.getAllEmployees();
            int averageSalary = sumSalary()/employees.size();
            List<Employee> highSalary = new ArrayList<>();
            for (Employee employee : employees){
                if (employee.getSalary() > averageSalary){
                    highSalary.add(employee);
                }
            }

            return highSalary;
        }


    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);

    }

    @Override
    public void editEmployee(int id, Employee employee) {
        employeeRepository.editEmployee(id,employee);

    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);

    }

    @Override
    public List<Employee> getEmployeesWithSalaryHigherThan(int salary) {
        return employeeRepository.getEmployeesWithSalaryHigherThan(salary);
    }
}

