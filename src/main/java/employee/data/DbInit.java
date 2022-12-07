package employee.data;

import employee.io.DbAccess;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 05. Dezember 2022<br>
 * <b>Time:</b> 13:01<br>
 */

@Component
public class DbInit {


    @PostConstruct
    public void test () {
        DbAccess.readJson().forEach(System.out::println);
    }
}
