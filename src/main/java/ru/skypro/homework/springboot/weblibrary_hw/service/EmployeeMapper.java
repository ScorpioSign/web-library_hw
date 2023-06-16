package ru.skypro.homework.springboot.weblibrary_hw.service;

import org.springframework.stereotype.Component;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());



        return employeeDTO;

    }

    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        return employee;
    }

    public static EmployeeFullInfo toEmployeeFullInfo(Employee employee) {
        EmployeeFullInfo employeeFullInfo = new EmployeeFullInfo();
        employeeFullInfo.setId(employee.getId());
        employeeFullInfo.setName(employee.getName());
        employeeFullInfo.setSalary(employee.getSalary());
        employeeFullInfo.setPositionName(employee.getPosition().getName());
        return employeeFullInfo;
    }

    static List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeeList) {
        return employeeList
                .stream()
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }
}