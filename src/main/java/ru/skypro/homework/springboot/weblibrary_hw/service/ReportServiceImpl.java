package ru.skypro.homework.springboot.weblibrary_hw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public int createReport() throws IOException {
        logger.info("Вызван метод создания отчёта и записи его в файл");
        int id = (int) reportRepository.count();
        String fileName = fileName(id);
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeReportDTO> employeeReport = reportRepository.createReport();
        logger.debug("Получены данные сотрудников из БД");
        String json = objectMapper.writeValueAsString(employeeReport); // JSON-строка
        System.out.println(json);
        // записываем файл
        File file = new File("src/main/java/" + fileName);
        Files.writeString(file.toPath(), json);

        Report report = new Report(id + 1, file.getPath());;
        reportRepository.save(report);
        logger.debug("Данные сохранены в файл");
        return report.getId();
    }

    private String fileName(int id) {
        //int id = (int) reportRepository.count();
        String fileName = "report" + id + ".json";
        return fileName;
    }

    @Override
    public String getReportById(int id) throws IOException, IncorrectIdException {
        logger.info("Вызван метод получения отчета в формате JSON по id={} ", id);
        Report report = reportRepository.findById(id).orElseThrow(() -> {
            logger.error("Не найден файл с id " + id);
            return new IncorrectIdException();
        });
//        String fileName = report.getPath();
//        Resource resource = new ByteArrayResource(Files.readAllBytes(Path.of(fileName)));
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(resource);
        return report.getPath();
    }


}
