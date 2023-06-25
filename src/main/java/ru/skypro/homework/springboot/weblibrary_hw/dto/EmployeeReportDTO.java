package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor


public class EmployeeReportDTO implements Serializable {
    public EmployeeReportDTO(String departmentName, Long numberOfEmployees, int maxSalary, int minSalary, Double averageSalary) {
        this.departmentName = departmentName;
        this.numberOfEmployees = numberOfEmployees;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
    }

    //private int id;
    private String departmentName;
    private Long numberOfEmployees;
    private int maxSalary;
    private int minSalary;
    private Double averageSalary;

    @Override
    public String toString() {
        return "Отчет по отделам \n" +
                "Название отдела " + departmentName + '\n' +
                "Количество сотрудников " + numberOfEmployees + '\n' +
                "Минимальная зарплата по отделу " + minSalary + '\n' +
                "Максимальная зарплата по отделу " + maxSalary + '\n' +
                "Средняя зарплата " + averageSalary + '\n';
    }
}
