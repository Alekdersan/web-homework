package pro.sky.java.cours2.webhomework.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.List;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.java.cours2.webhomework.EmployeeTestConstants.*;



@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void mustFindEmployeeWithMaxSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertEquals(MAX_SALARY_EMPLOYEE, out.employeeWithMaxSalary(DEPARTMENT));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionIfEmployeeWithMaxSalaryInEmptyList() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMaxSalary(DEPARTMENT));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionIfEmployeeWithMaxSalaryIsNotDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMaxSalary(BED_DEPARTMENT));
    }

    @Test
    public void mustFindEmployeeWithMinSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertEquals(MAX_SALARY_EMPLOYEE, out.employeeWithMaxSalary(DEPARTMENT));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionIfEmployeeWithMinSalaryInEmptyList() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMinSalary(DEPARTMENT));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionIfEmployeeWithMinSalaryIsNotDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMinSalary(BED_DEPARTMENT));
    }

    @Test
    public void mustReturnDepartmentEmployeesIfEmployeesPartOfDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(List.of(MAX_SALARY_EMPLOYEE), out.findEmployeesByDepartment(DEPARTMENT));
        assertEquals(singletonList(DIFFERENT_DEPARTMENT_EMPLOYEE), out.findEmployeesByDepartment(BED_DEPARTMENT));
    }

    @Test
    public void mustReturnEmptyListIfEmployeesDontFoundDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertEquals(emptyList(), out.findEmployeesByDepartment(BED_DEPARTMENT));
    }

    @Test
    public void mustReturnAllEmployeesIfTheyExist() {
        when(employeeService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(DEPARTMENT_MAP, out.findAllEmployees());
    }

    @Test
    public void mustReturnEmptyMapEmployeesIfTheyExist() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());

        assertEquals(emptyMap(), out.findAllEmployees());
    }
}
