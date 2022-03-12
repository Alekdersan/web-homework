package pro.sky.java.cours2.webhomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName,
                              @RequestParam String lastName) {
        Employee result = employeeService.addEmployee(firstName, lastName);
        return generateMessage(result, "успешно создан");
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam ("firstName") String firstName,
                                 @RequestParam ("lastName") String lastName) {
        Employee result = employeeService.removeEmployee(firstName, lastName);
        return generateMessage(result, "удален");
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam ("firstName") String firstName,
                                 @RequestParam ("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/all")
    public Collection<Employee> allEmployee() {
        return employeeService.getAll();
    }

    private String generateMessage(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(),
                employee.getLastName(),
                status);

    }
}
