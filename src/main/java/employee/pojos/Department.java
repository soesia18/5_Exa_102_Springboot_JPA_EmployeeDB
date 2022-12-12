package employee.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 05. Dezember 2022<br>
 * <b>Time:</b> 12:50<br>
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Department {
    @Id
    @JsonAlias("number")
    private String deptNo;
    @JsonAlias("name")
    private String deptName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee deptManager;

    public void setDeptManager(Employee deptManager) {
        deptManager.setDepartment(this);
        this.deptManager = deptManager;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    public void setEmployees(List<Employee> employees) {
        employees.forEach(employee -> employee.setDepartment(this));
        this.employees = employees;
    }
}
