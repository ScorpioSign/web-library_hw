package ru.skypro.homework.springboot.weblibrary_hw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.springboot.weblibrary_hw.service.ReportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        String fileName = reportService.getReportById(id);
        Resource resource = new ByteArrayResource(Files.readAllBytes(Path.of(fileName)));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}



