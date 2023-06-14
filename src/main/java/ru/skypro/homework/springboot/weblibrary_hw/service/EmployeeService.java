package ru.skypro.homework.springboot.weblibrary_hw.service;

import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;

import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import java.util.List;

public interface EmployeeService {

    // метод возвращает полный список сотрудников
    List<EmployeeDTO> getAllEmployees();

  // метод возвращает сумму зарплат
     int sumSalary();

     EmployeeDTO minSalary();

     EmployeeDTO maxSalary();

     List<EmployeeDTO> salaryAboveAverage();

    EmployeeDTO getEmployeeById(int id) throws IncorrectIdException;


    // метод дабавляет сотрудника в базу
    void addEmployee(EmployeeDTO employee);


    // метод для изменения данных сотрудника
    void editEmployee(int id, EmployeeDTO employeeDTO) throws IncorrectIdException;



    // метод удаляет сотрудника по id
    void deleteEmployee(int id) throws IncorrectIdException;

    List<EmployeeDTO> getEmployeesWithSalaryHigherThan(int salary);

    // возвращает всех сотрудников с должностями
    List<EmployeeFullInfo> getAllEmployeeFullInfo();

    // возвращает сотрудника по id с должностью
    EmployeeFullInfo getAllEmployeeByIdFullInfo(int id) throws IncorrectIdException;

    // метод возвращает список сотрудников по позиции
    List<EmployeeDTO> getEmployeeByPositionName(String position);

    // метод возвращает информацию о сотрудниках, основываясь на номере страницы.
    List<EmployeeDTO> getEmployeeFromPage(int page);



}


