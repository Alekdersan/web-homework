package pro.sky.java.cours2.webhomework;

import pro.sky.java.cours2.webhomework.data.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeTestConstants {
    public static final String FIRST_NAME = "Grigory";
    public static final String FIRST_NAME2 = "Anna";
    public static final String LAST_NAME = "Rasputin";
    public static final String LAST_NAME2 = "Karenina";
    public static final int SALARY = 1000;
    public static final int MIN_SALARY = 100;
    public static final String DEPARTMENT = "The Royal court";
    public static final String BED_DEPARTMENT = "Merchant's yard";
    public static final Employee MAX_SALARY_EMPLOYEE =  new Employee(FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY);
    public static final Employee MIN_SALARY_EMPLOYEE =  new Employee(FIRST_NAME2, LAST_NAME2, DEPARTMENT, MIN_SALARY);
    public static final Set<Employee> EMPLOYEES =  Set.of(MIN_SALARY_EMPLOYEE, MAX_SALARY_EMPLOYEE);
    public static final Employee DIFFERENT_DEPARTMENT_EMPLOYEE =  new Employee(FIRST_NAME, LAST_NAME, BED_DEPARTMENT, MIN_SALARY);
    public static final Set<Employee> DIFFERENT_DEPARTMENT_EMPLOYEES =  Set.of(MAX_SALARY_EMPLOYEE, DIFFERENT_DEPARTMENT_EMPLOYEE);
    public static final Map<String, List<Employee>> DEPARTMENT_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES.stream()
            .collect(groupingBy(Employee::getDepartment));
}
