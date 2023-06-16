package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeFullInfo {
private int id;
    private String name;
    private int salary;
    private String positionName;
}
