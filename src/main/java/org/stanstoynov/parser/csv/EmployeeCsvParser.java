package org.stanstoynov.parser.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.stanstoynov.model.Employee;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCsvParser {

    public static List<Employee> parseEmployees(File file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        csvMapper.registerModule(module);

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(Employee.class)
                .withColumnSeparator(',');

        List<Employee> employees;

        try (MappingIterator<Employee> mappingIterator = csvMapper
                .readerWithTypedSchemaFor(Employee.class)
                .with(csvSchema)
                .readValues(file)
        ) {
            employees = mappingIterator.readAll();
        }

        return employees;
    }

}
