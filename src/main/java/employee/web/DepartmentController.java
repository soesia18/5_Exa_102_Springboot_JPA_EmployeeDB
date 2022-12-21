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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@RequestMapping("/department")
@SessionAttributes({"departments", "actualDepartment"})
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;


    public DepartmentController(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @ModelAttribute("actualDepartment")
    public Department department() {
        return null;
    }


    @ModelAttribute("departments")
    public List<Department> departments() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> {
            department.setEmployees(department.getEmployees().stream().filter(employee -> !employee.equals(department.getDeptManager())).collect(Collectors.toList()));
        });
        return departments;
    }

    /*
    @PostConstruct
    private void loadDepartments() {
        this.departments = departmentRepository.findAll();
    }
    */

    @GetMapping
    public ModelAndView showAllDepartments(Model model) {
        log.debug("GET request to /department");

        List<Department> departments = departments();
        model.addAttribute("departments", departments);

        if (!departments.isEmpty() && model.getAttribute("actualDepartment") == null) {
            model.addAttribute("actualDepartment", new Department(getDepartment(departments.get(0).getDeptNo())));
        } else {
            model.addAttribute("actualDepartment", getDepartment(((Department) Objects.requireNonNull(model.getAttribute("actualDepartment"))).getDeptNo()));
        }

        return new ModelAndView("departmentView");
    }

    @PostMapping
    public ModelAndView loadEmployee(Model model,
                                     @ModelAttribute("actualDepartment") Department actualDepartment) {
        log.debug("POST request to /department");
        model.addAttribute("actualDepartment", getDepartment(actualDepartment.getDeptNo()));
        return new ModelAndView("departmentView");
    }

    private Department getDepartment (String deptNo) {
        Optional<Department> optionalDepartment = departmentRepository.findById(deptNo);
        optionalDepartment.ifPresent(department -> department.setEmployees(department.getEmployees().stream().filter(employee -> !employee.equals(department.getDeptManager())).collect(Collectors.toList())));

        return optionalDepartment.get();
    }
}
