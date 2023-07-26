package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDTO implements Serializable {
    private int id;
    private String name;
    private int salary;
    private String departmentName;

    public EmployeeDTO(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Сотрудник : id ").append(id).append(", ")
                .append(name).append(", ")
                .append("зарплата: ").append(salary).append(", ")
                .append("отдел: ").append(departmentName);
        return sb.toString();
    }

}