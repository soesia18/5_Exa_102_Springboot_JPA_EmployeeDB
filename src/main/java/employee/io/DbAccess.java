package employee.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import employee.pojos.Department;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 05. Dezember 2022<br>
 * <b>Time:</b> 13:04<br>
 */

public class DbAccess {
    public static List<Department> readJson (){
        List<Department> departments = new ArrayList<>();

        File file =
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources","employeedb.json").toFile();

        ObjectMapper mapper = new ObjectMapper();

        try {
            departments = mapper.readValue(file, new TypeReference<List<Department>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return departments;
    }
}
