package employee.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import employee.util.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
public class Employee {
    @Id
    @JsonAlias("emp_no")
    private Integer employeeNo;
    @Column(length = 14)
    private String firstname;
    @Column(length = 16)
    private String lastname;
    @Column(length = 5)
    private Gender gender;
    @JsonAlias("birthDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Department department;

}
