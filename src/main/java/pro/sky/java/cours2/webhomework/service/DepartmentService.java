package pro.sky.java.cours2.webhomework.service;

import pro.sky.java.cours2.webhomework.data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface DepartmentService {
    Employee employeeWithMaxSalary(String department);

    Employee employeeWithMinSalary(String department);

    Collection<Employee> findEmployeesByDepartment(String department);

    Map<String, List<Employee>> findAllEmployees();
}
