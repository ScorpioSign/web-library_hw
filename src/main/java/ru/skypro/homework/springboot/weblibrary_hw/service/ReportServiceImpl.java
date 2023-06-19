package ru.skypro.homework.springboot.weblibrary_hw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO;

import ru.skypro.homework.springboot.weblibrary_hw.entity.Report;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;

import ru.skypro.homework.springboot.weblibrary_hw.repository.ReportRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;


    @Override
    public int createReport() throws IOException {


        String fileName = fileName();
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeReportDTO> employeeReport = reportRepository.createReport();
        String json = objectMapper.writeValueAsString(employeeReport); // JSON-строка
        System.out.println(json);
        // записываем файл
        File file = new File("C:\\Users\\ы\\IdeaProjects\\web-library_hw\\src\\main\\java\\ru\\skypro\\homework\\springboot\\weblibrary_hw\\" + fileName);
        Files.writeString(file.toPath(), json);

        Report report = new Report();
        reportRepository.save(report);
        return report.getId();
    }

    private String fileName() {
        int id = (int) reportRepository.count();
        return "report" + id + ".json";
    }

    @Override
    public ResponseEntity<Resource> getReportById(int id) throws IOException, IncorrectIdException {
        Report report = reportRepository.findById(id).orElseThrow(IncorrectIdException::new);
        File file = report.getFile();
        String fileName = file.getName();
        Resource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}