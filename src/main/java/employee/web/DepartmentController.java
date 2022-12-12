package employee.web;

import employee.database.DepartmentRepository;
import employee.database.EmployeeRepository;
import employee.pojos.Department;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 07. Dezember 2022<br>
 * <b>Time:</b> 09:57<br>
 */

@Controller
@Slf4j
@DependsOn("initDatabase")
@RequestMapping("/")
public class DepartmentController {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    private List<Department> departments = new ArrayList<>();

    public DepartmentController(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    private void loadDepartments () {
        this.departments = departmentRepository.findAll();
    }

    @GetMapping
    public String showAllDepartments (Model model) {
        log.debug("GET request to /");

        model.addAttribute("departments", departments);

        return "departmentView";
    }
}
