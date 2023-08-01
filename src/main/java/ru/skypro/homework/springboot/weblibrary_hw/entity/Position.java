package ru.skypro.homework.springboot.weblibrary_hw.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "position")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode

public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


}
