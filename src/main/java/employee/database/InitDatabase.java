package employee.database;

import employee.io.DbAccess;
import employee.pojos.Department;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 05. Dezember 2022<br>
 * <b>Time:</b> 13:01<br>
 */

@Component
public class InitDatabase {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public InitDatabase(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void insertData () {
        List<Department> departmentList = DbAccess.readJson();
        departmentRepository.saveAll(departmentList);

    }
}
