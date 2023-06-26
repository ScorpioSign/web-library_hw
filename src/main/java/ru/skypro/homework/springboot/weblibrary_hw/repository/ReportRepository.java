package ru.skypro.homework.springboot.weblibrary_hw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Report;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    @Query("SELECT new ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO(\n " +
            " e.departmentName, \n" +
            " count(e.id), \n " +
            " min(e.salary), \n " +
            " max(e.salary), \n " +
            " avg(e.salary)) \n " +
            " FROM Employee e join fetch Position p \n" +
            " WHERE e.position = p \n" +
            " GROUP BY e.departmentName")
    List<EmployeeReportDTO> createReport();


}
