package ru.skypro.homework.springboot.weblibrary_hw.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeDTO;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;
import ru.skypro.homework.springboot.weblibrary_hw.exceptions.IncorrectIdException;
import ru.skypro.homework.springboot.weblibrary_hw.repository.EmployeeRepository;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service


public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

//    @PostConstruct
//    public void init() {
//        employeeRepository.deleteAll();
//        employeeRepository.saveAll(List.of(new Employee("Катя", 90_000),
//                new Employee("Дима", 102_000),
//                new Employee("Олег", 80_000),
//                new Employee("Вика", 165_000))
//        );
//    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Вызван метод получения списка сотрудников");
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAllEmployees().forEach(employeeList::add);
        logger.debug("Получен список сотрудников {} ", employeeList);
        return employeeList.stream()
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeFullInfo> getAllEmployeeFullInfo() {
        logger.info("Вызываем метод для получения списка сотрудников с указанием отдела");
        return employeeRepository.findAllEmployeeFullInfo();
            }


    @Override
    public EmployeeFullInfo getAllEmployeeByIdFullInfo(int id) throws IncorrectIdException {
        logger.info("Вызвар метод для получения сотрудника по id={} с наименованием отдела", id);
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toEmployeeFullInfo)
                //.orElseThrow(IncorrectIdException::new);
                .orElseThrow(() -> {
                    logger.error("Не найден сотрудник с id " + id);
                    return new IncorrectIdException();
                });
    }

    @Override
    public int sumSalary() {
        logger.info("Вызван метод подсчёта суммы зарплат сотрудников ");
        return employeeRepository.sumSalary();
    }

    @Override
    public EmployeeDTO minSalary() {
        logger.info("Вызван метод поиска сотрудника c минимальной зарплатой ");
        return EmployeeMapper.fromEmployee(employeeRepository.minSalary());


    }

    @Override
    public EmployeeDTO maxSalary() {
        logger.info("Вызван метод поиска сотрудника c максимальной зарплатой ");
        return EmployeeMapper.fromEmployee(employeeRepository.maxSalary());
    }

    @Override
    public List<EmployeeDTO> salaryAboveAverage() {
        logger.info("Вызван метод вывода списка сотрудников c максимальной зарплатой ");
        return employeeRepository.findAllEmployees().stream().
                filter(t -> t.getSalary() > employeeRepository.averageSalary())
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }


    @Override
//    public EmployeeDTO getEmployeeById(int id) throws IncorrectIdException {
//        return employeeRepository.findById(id)
//                .map(EmployeeMapper::fromEmployee)
//                .orElseThrow(IncorrectIdException::new);
//            }
    public EmployeeDTO getEmployeeById(int id)  {
        logger.info("Вызван метод поиска сотрудника по id={} ", + id );
     return employeeRepository.findById(id)
            .map(EmployeeMapper::fromEmployee)
            .orElseThrow(() -> {
             logger.error("Не найден сотрудник с id " + id);
             return new IncorrectIdException();
     }
     );


}

    @Override
    public void addEmployee(EmployeeDTO employee) {
        logger.info("Вызван метод добавления нового сотрудника " + employee);
        employeeRepository.save(EmployeeMapper.toEmployee(employee));

    }

    @Override
    public void editEmployee(int id, EmployeeDTO employeeDTO) throws IncorrectIdException {
        logger.info("Вызыван метод изменения данных сотрудника с id={} ", + id);
        Employee employee = employeeRepository.findById(id)
               // .orElseThrow(IncorrectIdException::new);
                .orElseThrow(() -> {
                    logger.error("Не найден сотрудник с id " + id);
                    return new IncorrectIdException();
                });
        if (!employeeDTO.getName().isBlank()) {
            employee.setName(employeeDTO.getName());
        }
        if (employeeDTO.getSalary() > 0) {
            employee.setSalary(employeeDTO.getSalary());
        }
        logger.debug("Новые даные сотрудника {} сохранены в БД", employee);
        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(int id) throws IncorrectIdException {
        logger.info("Вызван метод удаления сотрудника по id={} ",  + id );
        if (employeeRepository.existsById(id)) {
            logger.debug("Удален сотрудник с id={} ", id);
            employeeRepository.deleteById(id);

        } else {
            logger.error("Не найден сотрудник с id " + id);
            throw new IncorrectIdException();
        }
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(int salary) {
        logger.info("Вызван метод получения списка сотрудников с зарплатой выше значения {}", salary);
        return employeeRepository.findEmployeeBySalaryGreaterThan(salary)
                .stream()
                .map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }


    @Override
    public List<EmployeeDTO> getEmployeeByPositionName(String position) {
        logger.info("Вызван метод получения списка сотрудников, работающих в отделе {}", position);
        String pos = position.toLowerCase();
        if (!position.isBlank()) {
            logger.debug("Получен список сотрудников, работающих в отделе {}, из БД", position);
            return employeeRepository.findEmployeeByPosition_Name(pos)
                    .stream()
                    .map(EmployeeMapper::fromEmployee)
                    .collect(Collectors.toList());
        } else return getAllEmployees();
    }


    @Override
    public List<EmployeeDTO> getEmployeeFromPage(int page) {
        int sizePage = 10;
        logger.info("Вызван метод вывода списка сотрудников из БД, находящихся на {} листе. Размер листа - {}", page, sizePage);
        return employeeRepository.findAll(PageRequest.of(page, sizePage))
                .stream().map(EmployeeMapper::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
        logger.info("Вызван метод получения списка сотрудников c максимальной зарплатой ");
        List<Employee> employees = employeeRepository.withHighestSalary();
        return EmployeeMapper.toEmployeeDTOList(employees);
    }


    @Override
    public void uploadEmployeeFromFile(MultipartFile file) throws IOException {
        logger.info("Вызван метод для добавления списка сотрудников в базу данных из файла {}", file.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTO[] newEmployees = objectMapper.readValue(file.getBytes(), EmployeeDTO[].class);
        for (EmployeeDTO e : newEmployees) {
            addEmployee(e);
        }
    }


}

