package pro.sky.java.cours2.webhomework.service;

import pro.sky.java.cours2.webhomework.data.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAllEmployee();
}
