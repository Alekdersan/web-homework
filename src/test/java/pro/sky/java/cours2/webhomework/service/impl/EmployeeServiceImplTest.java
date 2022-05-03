package pro.sky.java.cours2.webhomework.service.impl;

import org.junit.jupiter.api.Test;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.exception.EmployeeExistsException;
import pro.sky.java.cours2.webhomework.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();
    @Test
    public void mustAddEmployeeIfHeDoesNotExist() {
        Employee expected = new Employee("Alexander", "Third", "Tsar", 100);

        assertEquals(0, out.getAllEmployees().size());
        assertFalse(out.getAllEmployees().contains(expected));

        Employee actual = out.addEmployee("Alexander", "Third", "Tsar", 100);

        assertEquals(expected,actual);
        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().contains(expected));
    }

    @Test
    public void mustThrowEmployeeExistsExceptionWhenHeWasExist() {

        Employee existed = out.addEmployee("Alexander", "Third", "Tsar", 100);

        assertTrue(out.getAllEmployees().contains(existed));
        assertThrows(EmployeeExistsException.class,() -> out.addEmployee("Alexander", "Third", "Tsar", 100));
    }

    @Test
    public void mustRemoveEmployeeWhenHeWasExist() {
        Employee expected = out.addEmployee("Alexander", "Third", "Tsar", 100);

        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().contains(expected));

        Employee actual = out.removeEmployee("Alexander", "Third", "Tsar", 100);
        assertEquals(expected, actual);
        assertTrue(out.getAllEmployees().isEmpty());
        assertFalse(out.getAllEmployees().contains(expected));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionWhenEmployeeDeletedWasAbsent() {
        assertEquals(0, out.getAllEmployees().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee("Alexander", "Third", "Tsar", 100));
    }

    @Test
    public void mustFindEmployeeWhenHeWasExist() {
        Employee expected = out.addEmployee("Alexander", "Third", "Tsar", 100);
        assertEquals(expected, out.findEmployee("Alexander", "Third"));
    }

    @Test
    public void mustThrowEmployeeNotFoundExceptionWhenEmployeeWasNotFound() {
        assertEquals(0, out.getAllEmployees().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee("Alexander", "Third"));
    }

    @Test
    public void mustGetAllEmployeesWhenTheyExist() {

        Employee employee1 = out.addEmployee("Alexander", "Third", "Tsar", 100);
        Employee employee2 = out.addEmployee("Nikolay", "Second", "Tsar", 200);
        Collection<Employee> expected = List.of(employee1, employee2);
        Collection<Employee> actual = out.getAllEmployees();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void mustGetEmptyCollectionWhenEmployeesDontExist() {
        assertIterableEquals(emptyList(), out.getAllEmployees());
    }
}