package pro.sky.java.cours2.webhomework.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.exception.EmployeeExistsException;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;
import pro.sky.java.cours2.webhomework.exception.InvalidNamesException;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.apache.tomcat.util.IntrospectionUtils.capitalize;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, String department, int salary) {
        validateNames(firstName, lastName);

        Employee newEmployee = new Employee(
                capitalize(firstName),
                capitalize(lastName),
                department,
                salary
        );

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeExistsException("Сотрудник уже есть в списке");
        }

        employees.put(getKey(firstName, lastName), newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, String department, int salary) {
        String key = getKey(firstName, lastName);
        if (employees.remove(key) == null) {
            throw new EmployeeNotFoundException("Сотрудника нет в списке");
        }
        return new Employee(firstName, lastName, department, salary);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);

        if (!employees.containsKey(getKey(firstName, lastName)))
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

    private void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                try {
                    throw new InvalidNamesException(name);
                } catch (InvalidNamesException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}