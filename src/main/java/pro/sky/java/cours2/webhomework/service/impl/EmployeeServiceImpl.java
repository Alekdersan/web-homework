package pro.sky.java.cours2.webhomework.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.exception.EmployeeExistsException;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, String department, int salary) {
        String key = getKey(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeExistsException("Сотрудник уже есть в списке");
        }
        Employee newEmployee = new Employee(firstName, lastName, department,salary);
        employees.put(key, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, String department, int salary) {
        String key = getKey(firstName, lastName);
        if (employees.remove(key) == null) {
            throw new EmployeeNotFoundException("Сотрудника нет в списке");
        }
        Employee removedEmployee = new Employee(firstName, lastName, department, salary);
        return removedEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудника нет в списке");
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }


    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}