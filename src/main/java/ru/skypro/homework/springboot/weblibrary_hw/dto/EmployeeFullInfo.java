package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class EmployeeFullInfo {
private int id;
    private String name;
    private int salary;
    private String positionName;
}
