package pro.sky.java.cours2.webhomework;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee addEmployee(Employee employee);

    Employee removeEmployee(String firstName, String lastName);

    Employee removeEmployee(Employee employee);

    Employee findEmployee(String firstName, String lastName);
}
