package employee.web;

import employee.database.DepartmentRepository;
import employee.database.EmployeeRepository;
import employee.pojos.Department;
import employee.pojos.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 07. Dezember 2022<br>
 * <b>Time:</b> 09:57<br>
 */

@Controller
@Slf4j
@RequestMapping("/employee")
@SessionAttributes({"departments", "actualDepartment"})
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ModelAndView loadAddEmployeePage() {

        log.debug("GET request to /employee");
        return new ModelAndView("employeeView");
    }

    @GetMapping("/{employeeId}")
    public ModelAndView deleteEmployee(@SessionAttribute("actualDepartment") Department actualDepartment,
                                       Model model,
                                       @PathVariable int employeeId) {
        log.debug("POST request to /employee");

        if (actualDepartment.getDeptManager().getEmployeeNo() != employeeId)
            employeeRepository.deleteById(employeeId);

        departmentRepository.findById(actualDepartment.getDeptNo())
                .ifPresent(department -> model.addAttribute("actualDepartment", department));

        return new ModelAndView("redirect:/department");
    }

    @ModelAttribute("newEmployee")
    public Employee employee () {
        return new Employee();
    }

    @GetMapping("/new")
    public ModelAndView newEmployee (Model model,
                                     @ModelAttribute("newEmployee") Employee employee,
                                     @SessionAttribute("actualDepartment") Department department) {

        employee.setDepartment(department);
        employeeRepository.save(employee);
        return new ModelAndView("redirect:/department");
    }
}
