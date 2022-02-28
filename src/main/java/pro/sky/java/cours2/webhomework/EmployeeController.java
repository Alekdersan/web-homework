package pro.sky.java.cours2.webhomework;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.addEmployee(firstName, lastName);
        return generateMessage(result, "успешно создан");
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.removeEmployee(firstName, lastName);
        return generateMessage(result, "удален");
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    private String generateMessage(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(),
                employee.getLastName(),
                status);
    }
}
