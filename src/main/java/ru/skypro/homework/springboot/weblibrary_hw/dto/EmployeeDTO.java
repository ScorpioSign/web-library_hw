package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {
    private int id;
    private String name;
    private int salary;
    private String departmentName;


}