package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.*;

import java.io.File;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReportDTO implements Serializable {
    private Integer id;
    private File data;


}
