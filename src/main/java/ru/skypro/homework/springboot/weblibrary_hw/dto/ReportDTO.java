package ru.skypro.homework.springboot.weblibrary_hw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO implements Serializable {
    private Integer id;
    private File data;


}
