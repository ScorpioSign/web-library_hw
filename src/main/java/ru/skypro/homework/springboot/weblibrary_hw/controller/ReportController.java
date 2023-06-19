package ru.skypro.homework.springboot.weblibrary_hw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.springboot.weblibrary_hw.service.ReportService;

import java.io.IOException;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {


    private final ReportService reportService;


    @PostMapping(value = "/")
    public int createReport() throws IOException {
        return reportService.createReport();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Resource> getReportById(@PathVariable Integer id) throws IOException {
        return reportService.getReportById(id);
    }
}



