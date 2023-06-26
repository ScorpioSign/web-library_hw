package ru.skypro.homework.springboot.weblibrary_hw.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo;
import ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeReportDTO;
import ru.skypro.homework.springboot.weblibrary_hw.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>, PagingAndSortingRepository {

    @Query("select new ru.skypro.homework.springboot.weblibrary_hw.dto.EmployeeFullInfo(e.id, e.name, e.salary, p.name) " +
            "from Employee e left join Position p where e.position.id = p.id")
    List<EmployeeFullInfo> findAllEmployeeFullInfo();

    List<Employee> findEmployeeByPosition_Name(String name);

    @Query("select sum(e.salary) from Employee e")
    Integer sumSalary();

    @Query("select avg(e.salary) from Employee e")
    Integer averageSalary();


    @Query(value = "select e from Employee e where e.salary = (SELECT MIN (salary) FROM Employee)")
    Employee minSalary();

    @Query(value = "select e from Employee e where e.salary = (SELECT MAX(salary) FROM Employee)")
    Employee maxSalary();


    List<Employee> findEmployeeBySalaryGreaterThan(int salary);

    @Query(value = "SELECT e FROM Employee e")
    List<Employee> findAllEmployees();


    @Query(value = "select e from Employee e where e.salary = (SELECT MAX(salary) FROM Employee)")
    List<Employee> withHighestSalary();


}
