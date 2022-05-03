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

        // создаем реальный объект Employee (элемент, параметр), но можно создать константы в отдельном классе/файле
        Employee expected = new Employee("Alexander", "Third", "Tsar", 100);

        // сравниваем ожидаемый (1-й) параметр с реальным (2-ым) параметром. Здесь объект EmployeeServiceImpl еще не содержит сущностей
        // и поэтому ожидаемый размер списка size = 0. (size/размер != capacity/вместимость, объем)
        assertEquals(0, out.getAllEmployees().size());

        // В строке 14 мы создали Employee, но еще не добалили в список/коллекцию. Поэтому проверяем что коллекция не содержит элемент.
        // Все что выполнилось внутри assertFalse() возвращает false, т.к. метод contains() не содержит элемент (мы его еще не добавили) и будет =S false.
        // Метод contains() – это метод Java, позволяющий проверить, содержит ли String другую подстроку или нет.
        assertFalse(out.getAllEmployees().contains(expected));

        // Здесь добавляем элемент-сотрудник (параметр с полями) с помощью метода addEmployee() из класса EmployeeServiceImpl.
        Employee actual = out.addEmployee("Alexander", "Third", "Tsar", 100);

        // сравниваем ожидаемый параметр (элемент, который мы создали в стр. 14) с реальным элементом,
        // который добавили (создали на 17 строке) в классе EmployeeServiceImpl.
        assertEquals(expected,actual);

        // сравниваем размер на изменение. Т.е. при добавлении сотрудника в пустой список (массив, коллекцию) размер size = 1.
        assertEquals(1, out.getAllEmployees().size());

        // проверяем на содержание/наличие элемента, т.е. что элемент содержится в коллекции (добавлен в полный список сотрудников).
        // Проверяем с помощью метода contains(), который в данном случае выдаст true. Все что выполнилось внутри assertTrue() возвращает true,
        // т.е. assertTrue = true.
        assertTrue(out.getAllEmployees().contains(expected));
    }

    @Test
    public void mustThrowEmployeeExistsExceptionWhenHeWasExist() {

        // для теста создаем реальный объект Employee (элемент, параметр), но можно использовать константы, созданные в отдельном классе/файле
        // который используем как ожидаемый. Его же и добавили в тесте выше
        Employee existed = out.addEmployee("Alexander", "Third", "Tsar", 100);

        // проверяем на наличие ожидаемого элемента existed в созданной коллекции getAllEmployees() в нашем объекте (по ссылке out).
        // contains(), в данном случае выдаст true, т.к. элемент был добавлен в коде/тесте выше. Т.о. assertTrue возвращает true.
        assertTrue(out.getAllEmployees().contains(existed));

        // Первый параметр принимает класс ожидаемого исключения (EmployeeExistsException.class),
        // второй параметр принимает лямбду, которая не имеет параметров и должна вызывать тестируемый метод out.addEmployee().
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
        // для проверки метода необходимо создать набор сотрудников (2-3) с заполненными полями
        Employee employee1 = out.addEmployee("Alexander", "Third", "Tsar", 100);
        Employee employee2 = out.addEmployee("Nikolay", "Second", "Tsar", 200);
                                                       // объявить ОЖИДАЕМЫЙ набор сотрудников и инициализировать созданных сотрудников в список List.of()
        Collection<Employee> expected = List.of(employee1, employee2);
                                                       // объявить РЕАЛЬНЫЙ набор сотрудников и инициализировать с out.getAllEmployees()
        Collection<Employee> actual = out.getAllEmployees();

        assertIterableEquals(expected, actual);       // сравнить ОЖИДАЕМЫЙ и РЕАЛЬНЫЙ списки сотрудников
    }

    @Test
    public void mustGetEmptyCollectionWhenEmployeesDontExist() {
        assertIterableEquals(emptyList(), out.getAllEmployees());
        // Метод assertIterableEquals() утверждает, что ожидаемые и фактические итерации
                                                                 // полностью равны.«Глубокое равенство» означает, что количество
                                                                // и порядок элементов в коллекции должны быть одинаковыми,
                                                                // а повторяющиеся элементы должны быть одинаковыми.
                                                          // Collections.emptyList() непреложный (immutable) список. Это означает, что вы не можете добавлять
                                                          // новые элементы к "пустому" списку. На фоне, каждый вызов метода Collections.emptyList()
                                                          // фактически не создает новый экземпляр пустого списка. Вместо этого, оно будет использовать
                                                          // снова уже существующий пустой экземпляр.
    }
}