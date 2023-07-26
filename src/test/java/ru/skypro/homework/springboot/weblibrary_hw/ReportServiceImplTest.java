package ru.skypro.homework.springboot.weblibrary_hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Report;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import ru.skypro.homework.springboot.weblibrary_hw.repository.EmployeeRepository;
import ru.skypro.homework.springboot.weblibrary_hw.repository.ReportRepository;
import ru.skypro.homework.springboot.weblibrary_hw.service.ReportServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private ReportRepository reportRepositoryMock;
    @InjectMocks
    private ReportServiceImpl out;

    public static final EmployeeReportDTO EmployeeReportDTOTest = new EmployeeReportDTO("DepartmentTest", 1L, 1, 1, 1.0);
    public static final List<EmployeeReportDTO> employeeReportDTOListTest = ReportDTOListTest();
    public static final Report reportTest = new Report(2, "src\\main\\java\\" + "report" + 1L + ".json");

    @Test
    void createReportTest() throws IOException {
        when(reportRepositoryMock.createReport()).thenReturn(employeeReportDTOListTest);
        when(reportRepositoryMock.count()).thenReturn(1L);
        assertEquals(reportTest.getId(), out.createReport());
        verify(reportRepositoryMock, times(1)).save(reportTest);
    }

    @Test
    void getReport_correctId() throws IOException, IncorrectIdException {
        when(reportRepositoryMock.findById(1)).thenReturn(Optional.of(reportTest));
        assertEquals(reportTest.getPath(), out.getReportById(1));
    }

    @Test
    void getReport_incorrectId() throws IOException, IncorrectIdException {
        when(reportRepositoryMock.findById(1)).thenThrow(IncorrectIdException.class);
        assertThrows(IncorrectIdException.class, () -> out.getReportById(1));
    }

    private static List<EmployeeReportDTO> ReportDTOListTest() {
        List<EmployeeReportDTO> result = new ArrayList<>();
        int i = 10;
        while (i > 0) {
            result.add(new EmployeeReportDTO(
                    EmployeeReportDTOTest.getDepartmentName() + i,
                    EmployeeReportDTOTest.getNumberOfEmployees() + i,
                    EmployeeReportDTOTest.getMaxSalary() + i,
                    EmployeeReportDTOTest.getMinSalary() + i,
                    EmployeeReportDTOTest.getAverageSalary() + i
            ));
            i = i - 1;
        }
        return result;
    }
}