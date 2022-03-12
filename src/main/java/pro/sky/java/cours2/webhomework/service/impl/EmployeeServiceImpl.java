package pro.sky.java.cours2.webhomework.service.impl;


import org.springframework.stereotype.Service;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.exception.EmployeeExistsException;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return addEmployee(newEmployee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            throw new EmployeeExistsException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return removeEmployee(newEmployee);
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        if (employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }


    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return List.copyOf(employees);

    }
}