package org.stanstoynov.parser.csv;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.stanstoynov.model.Employee;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    public EmployeeDeserializer() {
        this(null);
    }

    public EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Integer employeeId = Integer.parseInt(node.get("employeeId").asText().trim());
        Integer projectId = Integer.parseInt(node.get("projectId").asText().trim());
        LocalDate dateFrom = LocalDate.parse(node.get("dateFrom").asText().trim());

        String dateToValue = node.get("dateTo").asText().trim();
        LocalDate dateTo = dateToValue.equalsIgnoreCase("null") ? null : LocalDate.parse(dateToValue);

        return new Employee(employeeId, projectId, dateFrom, dateTo);
    }
}