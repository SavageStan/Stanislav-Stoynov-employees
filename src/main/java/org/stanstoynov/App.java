package org.stanstoynov;

import org.stanstoynov.model.Employee;
import org.stanstoynov.parser.csv.EmployeeCsvParser;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EmployeeCsvParser employeeCsvParser = new EmployeeCsvParser();
        List<Employee> employees;

        try {
            employees = employeeCsvParser.parseEmployees("employees.csv");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV file");
        }

        System.out.println(employees);
    }
}
