package org.stanstoynov.parser.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.stanstoynov.deserializer.EmployeeDeserializer;
import org.stanstoynov.model.Employee;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EmployeeCsvParser {

    public List<Employee> parseEmployees(String fileName) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        csvMapper.registerModule(module);

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(Employee.class)
                .withColumnSeparator(',');

        File csvFile = loadFileFromResources(fileName);
        List<Employee> employees;

        try (MappingIterator<Employee> mappingIterator = csvMapper
                .readerWithTypedSchemaFor(Employee.class)
                .with(csvSchema)
                .readValues(csvFile)
        ) {
            employees = mappingIterator.readAll();
        }

        return employees;
    }

    private File loadFileFromResources(String fileName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(String.format("File with file name %s not found", fileName));
        }

        return new File(url.getFile());
    }
}
