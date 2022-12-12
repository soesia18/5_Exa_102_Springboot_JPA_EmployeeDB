package employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 07. Dezember 2022<br>
 * <b>Time:</b> 09:57<br>
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String loadAddEmployeePage () {
        return "employeeView";
    }


}
