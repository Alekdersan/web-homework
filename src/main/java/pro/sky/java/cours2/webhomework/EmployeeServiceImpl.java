package pro.sky.java.cours2.webhomework;


import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Employee[] employees;
    private int size;

    public EmployeeServiceImpl() {
        employees = new Employee[5];
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return addEmployee(newEmployee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
    if (size == employees.length) {
            throw new EmployeeBookOverflowException();
        }
        int index = indexOf(employee);

        if (index != -1) {
            throw new EmployeeExistsException();
        }
        employees[size++] = employee;
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return removeEmployee(newEmployee);
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        int index = indexOf(employee);

        if (index != -1) {
            Employee result = employees[index];
            System.arraycopy(employees, index + 1, employees, index, size - index);
            employees[--size] = null;
            return result;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        int index = indexOf(newEmployee);

        if (index != -1) {
            return employees[index];
        }
        throw new EmployeeNotFoundException();
    }

    private int indexOf(Employee employee) {
        for (int i = 0; i < size; i++) {
            if (employees[i].equals(employee)) {
                return i;
            }
        }
        return -1;
    }
}