package employee.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_102_Springboot_JPA_EmployeeDB<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 07. Dezember 2022<br>
 * <b>Time:</b> 08:45<br>
 */

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.UK);
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return LocalDate.parse(deserializationContext.readValue(jsonParser, String.class), dTF);
    }
}
