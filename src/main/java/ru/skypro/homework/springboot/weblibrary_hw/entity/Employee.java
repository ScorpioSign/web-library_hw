package ru.skypro.homework.springboot.weblibrary_hw.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private int salary;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
