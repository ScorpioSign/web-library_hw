package ru.skypro.homework.springboot.weblibrary_hw.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.File;

@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors (chain = true)

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "file", columnDefinition = "text")
    File file;

    public Report(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Отчет: " +
                "номер отчета: " + id +
                ", содержание: " + file + '\n';
    }
}
