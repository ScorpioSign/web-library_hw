package ru.skypro.homework.springboot.weblibrary_hw.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Report;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;

import java.io.IOException;


public interface ReportService {
   // ResponseEntity<Resource> getReportById(int id) throws IOException, IncorrectIdException;
    String getReportById(int id) throws IOException;
    int createReport() throws IOException;


}
