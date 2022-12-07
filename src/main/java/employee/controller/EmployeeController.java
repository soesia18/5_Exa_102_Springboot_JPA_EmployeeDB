package employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 05. Dezember 2022<br>
 * <b>Time:</b> 13:00<br>
 */

@Controller
public class EmployeeController {
    @GetMapping
    public String show () {
        return "";
    }
}
