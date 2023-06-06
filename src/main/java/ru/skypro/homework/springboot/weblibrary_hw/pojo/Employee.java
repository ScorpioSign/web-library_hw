package ru.skypro.homework.springboot.weblibrary_hw.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Employee {

    private int id;
    private String name;
    private int salary;

}
