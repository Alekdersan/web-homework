package pro.sky.java.cours2.webhomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.cours2.webhomework.data.Employee;
import pro.sky.java.cours2.webhomework.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalary(@RequestParam("department") String department) {
        return departmentService.employeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalary(@RequestParam("department") String department) {
        return departmentService.employeeWithMinSalary(department);
    }

    @GetMapping(path = "/all", params = "/department")
    public Collection<Employee> findEmployeeByDepartment(@RequestParam("department") String department) {
        return departmentService.findEmployeesByDepartment(department);
    }

    @GetMapping("/all")
    public Map<String, List<Employee>> findEmployeeByDepartment() {
        return departmentService.findAllEmployees();
    }
}
