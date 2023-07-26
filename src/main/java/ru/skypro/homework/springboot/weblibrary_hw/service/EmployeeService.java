package ru.skypro.homework.springboot.weblibrary_hw.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;

import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    // метод возвращает полный список сотрудников
    List<EmployeeDTO> getAllEmployees();

    // метод возвращает сумму зарплат
    int sumSalary();

    // метод возвращает сотрудника с минимальной зарплатой
    EmployeeDTO minSalary();

    // метод возвращает сотрудника с максимальной зарплатой
    EmployeeDTO maxSalary();

    // метод возвращает сотрудников с зарплатой выше средней
    List<EmployeeDTO> salaryAboveAverage();

    EmployeeDTO getEmployeeById(int id) throws IncorrectIdException;


    // метод дабавляет сотрудника в базу
    void addEmployee(EmployeeDTO employee);


    // метод для изменения данных сотрудника
    Employee editEmployee(int id, EmployeeDTO employeeDTO) throws IncorrectIdException;


    // метод удаляет сотрудника по id
    void deleteEmployee(int id) throws IncorrectIdException;

    //возвращает сотрудникщи с зарплатой выше заданного
    List<EmployeeDTO> getEmployeesWithSalaryHigherThan(int salary);

    // возвращает всех сотрудников с должностями
    List<EmployeeFullInfo> getAllEmployeeFullInfo();

    // возвращает сотрудника по id с должностью
    EmployeeFullInfo getAllEmployeeByIdFullInfo(int id) throws IncorrectIdException;


    // метод возвращает список сотрудников по позиции
    List<EmployeeDTO> getEmployeeByPositionName(String position);

    // метод возвращает информацию о сотрудниках, основываясь на номере страницы.
    List<EmployeeDTO> getEmployeeFromPage(int page);

    // метод возвращает список сотрудников с максимальной зарплатой
    List<EmployeeDTO> withHighestSalary();

    void uploadEmployeeFromFile(MultipartFile file) throws IOException;


}


