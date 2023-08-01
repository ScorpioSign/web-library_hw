package ru.skypro.homework.springboot.weblibrary_hw.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private int salary;
    private String departmentName;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(Integer id, String name, int salary, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
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
