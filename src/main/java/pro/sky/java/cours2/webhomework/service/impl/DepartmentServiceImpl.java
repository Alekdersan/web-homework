package pro.sky.java.cours2.webhomework.service.impl;


import org.springframework.stereotype.Service;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;
import pro.sky.java.cours2.webhomework.service.DepartmentService;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee employeeWithMaxSalary(String department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден."));
    }

    @Override
    public Employee employeeWithMinSalary(String department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Этот сотрудник не найден."));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartment(String department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Employee>> findAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

