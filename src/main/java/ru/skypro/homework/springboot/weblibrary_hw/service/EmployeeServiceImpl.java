package ru.skypro.homework.springboot.weblibrary_hw.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import ru.skypro.homework.springboot.weblibrary_hw.repository.EmployeeRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

//    @PostConstruct
//    public void init() {
//        employeeRepository.deleteAll();
//        employeeRepository.saveAll(List.of(new Employee("Катя", 90_000),
//                new Employee("Дима", 102_000),
//                new Employee("Олег", 80_000),
//                new Employee("Вика", 165_000))
//        );
//    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAllEmployees().forEach(employeeList::add);
        return employeeList.stream()
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeFullInfo> getAllEmployeeFullInfo() {
        return employeeRepository.findAllEmployeeFullInfo();
    }


    @Override
    public EmployeeFullInfo getAllEmployeeByIdFullInfo(int id) throws IncorrectIdException {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toEmployeeFullInfo)
                .orElseThrow(IncorrectIdException::new);


    }

    @Override
    public int sumSalary() {

        return employeeRepository.sumSalary();
    }

    @Override
    public EmployeeDTO minSalary() {

        return EmployeeMapper.fromEmployee(employeeRepository.minSalary());


    }

    @Override
    public EmployeeDTO maxSalary() {

        return EmployeeMapper.fromEmployee(employeeRepository.maxSalary());
    }

    @Override
    public List<EmployeeDTO> salaryAboveAverage() {

        return employeeRepository.findAllEmployees().stream().
                filter(t -> t.getSalary() > employeeRepository.averageSalary())
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }


    @Override

    public EmployeeDTO getEmployeeById(int id) throws IncorrectIdException {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::fromEmployee)
                .orElseThrow(IncorrectIdException::new);
    }

    @Override
    public void addEmployee(EmployeeDTO employee) {

        employeeRepository.save(EmployeeMapper.toEmployee(employee));

    }

    @Override
    public void editEmployee(int id, EmployeeDTO employeeDTO) throws IncorrectIdException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(IncorrectIdException::new);
        if (!employeeDTO.getName().isBlank()) {
            employee.setName(employeeDTO.getName());
        }
        if (employeeDTO.getSalary() > 0) {
            employee.setSalary(employeeDTO.getSalary());
        }
        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(int id) throws IncorrectIdException {


        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(int salary) {
        return employeeRepository.findEmployeeBySalaryGreaterThan(salary)
                .stream()
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }


    @Override
    public List<EmployeeDTO> getEmployeeByPositionName(String position) {
        String pos = position.toLowerCase();
        if (!position.isBlank()) {
            return employeeRepository.findEmployeeByPosition_Name(pos)
                    .stream()
                    .map(EmployeeMapper::fromEmployee)
                    .collect(Collectors.toList());
        } else return getAllEmployees();
    }


    @Override
    public List<EmployeeDTO> getEmployeeFromPage(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10))
                .stream().map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
        List<Employee> employees = employeeRepository.withHighestSalary();
        return EmployeeMapper.toEmployeeDTOList(employees);
    }


}

