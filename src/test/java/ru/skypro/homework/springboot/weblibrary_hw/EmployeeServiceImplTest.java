package ru.skypro.homework.springboot.weblibrary_hw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Position;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import ru.skypro.homework.springboot.weblibrary_hw.repository.EmployeeRepository;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeMapper;
import ru.skypro.homework.springboot.weblibrary_hw.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repositoryMock;

    @InjectMocks
    private EmployeeServiceImpl out;


    public static final Position positionTest = new Position(1, "designer");
    public static final Employee employeeTest = new Employee(1, "Sergey", 60000, positionTest);
    public static final EmployeeDTO employeeDTOTest = new EmployeeDTO(1, "Sergey", 60000);
    public static final EmployeeFullInfo employeeFullInfoTest = new EmployeeFullInfo(1, "Sergey", 60000, positionTest.getName());
    public static final List<Employee> employeeListTest = EmployeeListTest();
    public static final List<EmployeeDTO> employeeDTOListTest = EmployeeDTOListTest();

    @Test
    void salarySum() {
        when(repositoryMock.sumSalary()).thenReturn(10);
        assertEquals(10, out.sumSalary());
    }


    @Test
    void employeeMinSalary() {
        when(repositoryMock.minSalary()).thenReturn(employeeTest);
        assertEquals(employeeDTOTest, out.minSalary());
    }

    @Test
    void withHighestSalary() {
        when(repositoryMock.withHighestSalary()).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.withHighestSalary());
    }

    @Test
    void findBySalaryGreaterThan() {
        when(repositoryMock.findEmployeeBySalaryGreaterThan(10)).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.getEmployeesWithSalaryHigherThan(10));
    }

    @DisplayName("Проверка метода добавления нового сотрудника")
    @Test
    public void addNewEmployee() {

        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Иван", 120000, "department1");

        out.addEmployee(employeeDTO);

        verify(repositoryMock, times(1)).save(EmployeeMapper.toEmployee(employeeDTO));
    }

    @ParameterizedTest
    @MethodSource("dataForTesting_editEmployee")
    @DisplayName("Проверка метода изменения данных сотрудника c корректным Id")
    void editEmployee_correctId(EmployeeDTO employeeDTO, Employee employee, Employee employeeOutput) throws IncorrectIdException {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(employeeOutput));
        assertEquals(employee, out.editEmployee(employee.getId(), employeeDTO));
    }
    @Test
    @DisplayName("Проверка метода изменения данных сотрудника c некорректным Id")
    void editEmployee_incorrectId() throws IncorrectIdException {
        when(repositoryMock.findById(1)).thenThrow(IncorrectIdException.class);
        assertThrows(IncorrectIdException.class, () -> out.editEmployee(1, employeeDTOTest));
    }
    @Test
    @DisplayName("тест метода, возвращающего полный список сотрудников")
    void getAllEmployee() {
        when(repositoryMock.findAllEmployees()).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.getAllEmployees());
    }

       @Test
    @DisplayName("тест метода, возвращающего сотрудника по id; передаем верный id")
    void getEmployee_correctId() throws IncorrectIdException {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(employeeTest));
        assertEquals(employeeDTOTest.getSalary(), out.getEmployeeById(1).getSalary());
    }

    @Test
    @DisplayName("тест метода, возвращающего сотрудника по id; передаем неверный id")
    void getEmployee_InCorrectId() throws IncorrectIdException {
        when(repositoryMock.findById(99)).thenThrow(IncorrectIdException.class);
        assertThrows(IncorrectIdException.class, () -> out.getEmployeeById(99));
    }

       @Test
    @DisplayName("тест метода, возвращающего всех сотрудников с должностями")
    void getAllEmployeeFullInfo() {
        when(repositoryMock.findAllEmployeeFullInfo()).thenReturn(List.of(employeeFullInfoTest));
        assertEquals(employeeFullInfoTest, out.getAllEmployeeFullInfo().get(0));

    }

        @Test
    @DisplayName("тест метода, возвращающего сотрудника по id с должностью; корректный id")
    void getEmployeeByIdFullInfo_correctId() throws IncorrectIdException {
        when(repositoryMock.getEmployeeByIdFullInfo(1)).thenReturn(Optional.of(employeeFullInfoTest));
        assertEquals(employeeFullInfoTest, out.getAllEmployeeByIdFullInfo(1));
    }

    @Test
    @DisplayName("тест метода, возвращающего сотрудника по id с должностью; корректный id")
    void getEmployeeByIdFullInfo_incorrectId() throws IncorrectIdException {
        when(repositoryMock.getEmployeeByIdFullInfo(99)).thenThrow(IncorrectIdException.class);
        assertThrows(IncorrectIdException.class, () -> out.getAllEmployeeByIdFullInfo(99));
    }


    @Test
    @DisplayName("тест метода, возвращающего список сотрудников по позиции; корректное наименование позиции")
    void getEmployeeByPositionName_correctName() {
        when(repositoryMock.findEmployeeByPosition_Name("designer")).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.getEmployeeByPositionName("designer"));
    }

    @Test
    @DisplayName("тест метода, возвращающего список сотрудников по позиции; корректное наименование позиции с заглавной буквы")
    void getEmployeeByPositionName_correctName_CapitalLetter() {
        when(repositoryMock.findEmployeeByPosition_Name("designer")).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.getEmployeeByPositionName("Designer"));
    }

    @Test
    @DisplayName("тест метода, возвращающего список сотрудников по позиции; пустая строка вместо наименования позиции")
    void getEmployeeByPositionName_NameIsBlank() {
        when(repositoryMock.findAllEmployees()).thenReturn(employeeListTest);
        assertEquals(employeeDTOListTest, out.getEmployeeByPositionName(""));
    }

        @Test
    @DisplayName("тест метода,возвращающего информацию о сотрудниках по номеру страницы")
    void getEmployeeFromPage() {
        Page<Employee> employees = new PageImpl<>(employeeListTest);
        when(repositoryMock.findAll(PageRequest.of(1, 10))).thenReturn(employees);
        assertEquals(employeeDTOListTest, out.getEmployeeFromPage(1));
    }


    @Test
    @DisplayName("тест метода удаления сотрудника по id; корректный id")
    void deleteEmployee_correctId() throws IncorrectIdException {
        when(repositoryMock.existsById(1)).thenReturn(true);
        out.deleteEmployee(1);
        verify(repositoryMock, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("тест метода удаления сотрудника по id; некорректный id")
    void deleteEmployee_incorrectId() throws IncorrectIdException {
        when(repositoryMock.existsById(99)).thenReturn(false);
        assertThrows(IncorrectIdException.class, () -> out.deleteEmployee(99));
        verify(repositoryMock, times(0)).deleteById(99);
    }


    private static Stream<Arguments> dataForTesting_editEmployee() {
        return Stream.of(
                Arguments.of(new EmployeeDTO(1, "", 100),
                        new Employee(1, "Ivan", 100, positionTest),
                        new Employee(1, "Ivan", 142, positionTest)),
                Arguments.of(new EmployeeDTO(1, "Mary", 0),
                        new Employee(1, "Mary", 142, positionTest),
                        new Employee(1, "Ivan", 142, positionTest)),
                Arguments.of(new EmployeeDTO(1, "Mary", 111),
                        new Employee(1, "Mary", 111, positionTest),
                        new Employee(1, "Ivan", 142, positionTest))
        );
    }


    private static List<Employee> EmployeeListTest() {
        List<Employee> result = new ArrayList<>();
        int i = 10;
        while (i > 0) {
            result.add(new Employee(
                    i,
                    employeeTest.getName() + i,
                    employeeTest.getSalary() + i,
                    employeeTest.getPosition()
            ));
            i = i - 1;
        }
        return result;
    }

    private static List<EmployeeDTO> EmployeeDTOListTest() {
        List<EmployeeDTO> result = new ArrayList<>();
        int i = 10;
        while (i > 0) {
            result.add(new EmployeeDTO(
                    i,
                    employeeDTOTest.getName() + i,
                    employeeDTOTest.getSalary() + i

            ));
            i = i - 1;
        }
        return result;
    }


}
